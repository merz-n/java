package org.example;

import java.sql.Connection;

import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args){
        try(Connection connection = DatabaseConnection.getConnection()) {
            System.out.println("Соединение успешно установлено!");
        }catch (SQLException e){
            System.out.println("Соединение Не установлено установлено!");
            e.printStackTrace();
        }
    }
}
