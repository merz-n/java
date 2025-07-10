package org.example;

import org.example.database.Database;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = Database.getConnection()) {
            System.out.println("✅ Успешное подключение к базе данных!");
        } catch (SQLException e) {
            System.out.println("❌ Ошибка подключения: " + e.getMessage());
        }
    }
}
