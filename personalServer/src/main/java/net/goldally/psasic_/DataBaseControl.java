package net.goldally.psasic_;

import java.io.IOException;
import java.sql.*;

import static net.goldally.psasic_.misc.Reader.readFile;

public class DataBaseControl {
    public static Connection dbConnection;

    public static void init() throws SQLException, ClassNotFoundException, IOException {
        // Подключение к базе данных.
        Class.forName("org.sqlite.JDBC");
        dbConnection = DriverManager.getConnection("jdbc:sqlite:PERSONAL.db");

        // Создание базы данных.

        // Заполнение шаблонов запросов к БД.
    }
}
