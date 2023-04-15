package net.goldally.psasic_;

import java.sql.*;

public class DataBaseControl {
    public static Connection dbConnection;
    public static PreparedStatement UserCountStatement;
    public static PreparedStatement TableCreationStatement;
    public static PreparedStatement UserInsertStatement;

    public static void init() throws SQLException, ClassNotFoundException {
        // Подключение к базе данных.
        Class.forName("org.sqlite.JDBC");
        dbConnection = DriverManager.getConnection("jdbc:sqlite:SqliteJavaDB.db");

        //TableCreationStatement = dbConnection.prepareStatement("INSERT INTO users (name, password) VALUES (?, ?)");

        // Заполнение шаблонов запросов к БД.
        UserCountStatement = dbConnection.prepareStatement("SELECT COUNT(*) FROM users WHERE name=?");
        UserInsertStatement = dbConnection.prepareStatement("INSERT INTO users (name, password) VALUES (?, ?)");
    }
}
