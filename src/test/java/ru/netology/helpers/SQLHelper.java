package ru.netology.helpers;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/app";
    private static final String USER = "app";
    private static final String PASSWORD = "pass";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL Driver not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void testConnection() {
        try (Connection conn = getConnection()) {
            System.out.println("✅ Database connection successful");
        } catch (SQLException e) {
            System.out.println("❌ Database connection failed: " + e.getMessage());
            throw new RuntimeException("Database connection failed", e);
        }
    }

    public static void cleanDatabase() {
        var runner = new QueryRunner();
        var queries = new String[]{
                "DELETE FROM order_entity",
                "DELETE FROM payment_entity",
                "DELETE FROM credit_request_entity"
        };

        try (var conn = getConnection()) {
            for (String query : queries) {
                int affected = runner.update(conn, query);
                System.out.println("Cleaned " + affected + " rows from: " + query);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception during cleanup: " + e.getMessage());
        }
    }

    public static String getPaymentStatus() {
        return getStatus("payment_entity", "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1");
    }

    public static String getCreditStatus() {
        return getStatus("credit_request_entity", "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1");
    }

    private static String getStatus(String entityName, String sql) {
        var runner = new QueryRunner();

        try (var conn = getConnection()) {
            String status = runner.query(conn, sql, new ScalarHandler<String>());
            System.out.println("📊 " + entityName + " status: " + status);
            return status;
        } catch (SQLException e) {
            System.out.println("ℹ️ No records found in " + entityName + ": " + e.getMessage());
            return null;
        }
    }

    public static void waitForRecord(int maxWaitMs) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < maxWaitMs) {
            if (getPaymentStatus() != null || getCreditStatus() != null) {
                return;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        System.out.println("⏰ Timeout waiting for database record");
    }
}