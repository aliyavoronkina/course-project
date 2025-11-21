package ru.netology.helpers;

import java.sql.*;

public class SQLHelper {

    // ИСПРАВЛЯЕМ URL для Docker
    private static final String URL = "jdbc:mysql://localhost:3306/app";
    private static final String USER = "student";
    private static final String PASSWORD = "logviewer";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static String getPaymentStatus() {
        String sql = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getString("status");
            }
            return null;
        } catch (SQLException e) {
            System.err.println("Error getting payment status: " + e.getMessage());
            return null;
        }
    }

    public static String getCreditStatus() {
        String sql = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getString("status");
            }
            return null;
        } catch (SQLException e) {
            System.err.println("Error getting credit status: " + e.getMessage());
            return null;
        }
    }

    public static String getPaymentId() {
        String sql = "SELECT payment_id FROM order_entity ORDER BY created DESC LIMIT 1";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getString("payment_id");
            }
            return null;
        } catch (SQLException e) {
            System.err.println("Error getting payment ID: " + e.getMessage());
            return null;
        }
    }

    public static String getCreditId() {
        String sql = "SELECT credit_id FROM order_entity ORDER BY created DESC LIMIT 1";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getString("credit_id");
            }
            return null;
        } catch (SQLException e) {
            System.err.println("Error getting credit ID: " + e.getMessage());
            return null;
        }
    }

    public static void cleanDatabase() {
        String[] queries = {
                "DELETE FROM order_entity",
                "DELETE FROM payment_entity",
                "DELETE FROM credit_request_entity"
        };

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            for (String query : queries) {
                stmt.executeUpdate(query);
            }
        } catch (SQLException e) {
            System.err.println("Error cleaning database: " + e.getMessage());
        }
    }
}