package ru.netology.helpers;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static final QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConn() throws SQLException {
        String url = System.getProperty("db.url", "jdbc:mysql://localhost:3306/app");
        String user = "app";
        String password = "pass";
        return DriverManager.getConnection(url, user, password);
    }

    public static String getPaymentStatus() {
        var statusSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        try (var conn = getConn()) {
            return runner.query(conn, statusSQL, new ScalarHandler<String>());
        } catch (SQLException e) {
            System.out.println("Ошибка при получении статуса платежа: " + e.getMessage());
            return null;
        }
    }

    public static String getCreditStatus() {
        var statusSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        try (var conn = getConn()) {
            return runner.query(conn, statusSQL, new ScalarHandler<String>());
        } catch (SQLException e) {
            System.out.println("Ошибка при получении статуса кредита: " + e.getMessage());
            return null;
        }
    }

    public static void cleanDatabase() {
        try (var connection = getConn()) {
            try {
                runner.execute(connection, "DELETE FROM credit_request_entity");
                System.out.println("Таблица credit_request_entity очищена");
            } catch (SQLException e) {
                System.out.println("Таблица credit_request_entity не найдена, пропускаем");
            }
            try {
                runner.execute(connection, "DELETE FROM order_entity");
                System.out.println("Таблица order_entity очищена");
            } catch (SQLException e) {
                System.out.println("Таблица order_entity не найдена, пропускаем");
            }
            try {
                runner.execute(connection, "DELETE FROM payment_entity");
                System.out.println("Таблица payment_entity очищена");
            } catch (SQLException e) {
                System.out.println("Таблица payment_entity не найдена, пропускаем");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка подключения к БД: " + e.getMessage());
        }
    }
}