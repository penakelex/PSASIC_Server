package net.goldally.psasic_;

import java.io.IOException;
import java.sql.*;

import static net.goldally.psasic_.misc.Reader.readFile;

public class DataBaseControl {
    public static Connection dbConnection;

    public static PreparedStatement UsersTableCreationStatement;
    public static PreparedStatement SessionsTableCreationStatement;
    public static PreparedStatement UserInsertStatement;
    public static PreparedStatement UserCountStatement;

    //TODO: Авторизация
    public static PreparedStatement isUserPasswordStatement;
    public static PreparedStatement addUserSessionStatement;
    public static PreparedStatement removeSessionStatement;

    public static void init() throws SQLException, ClassNotFoundException, IOException {
        // Подключение к базе данных.
        Class.forName("org.sqlite.JDBC");
        dbConnection = DriverManager.getConnection("jdbc:sqlite:GLOBAL.db");

        // Создание базы данных.
        UsersTableCreationStatement = dbConnection.prepareStatement(readFile("sql/global/createUsersTable.sql"));
        SessionsTableCreationStatement = dbConnection.prepareStatement(readFile("sql/global/createSessionsTable.sql"));
        UsersTableCreationStatement.execute();
        SessionsTableCreationStatement.execute();

        // Заполнение шаблонов запросов к БД.
        UserCountStatement = dbConnection.prepareStatement("SELECT COUNT(*) FROM users WHERE username=?");
        UserInsertStatement = dbConnection.prepareStatement("INSERT INTO users (username, password_hash) VALUES (?, ?)");

    }
}
