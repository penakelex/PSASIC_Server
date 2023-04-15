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

    public static PreparedStatement isUserPasswordStatement;
    public static PreparedStatement isActualSessionStatement;
    public static PreparedStatement createSessionStatement;
    public static PreparedStatement removeSessionStatement;
    public static PreparedStatement removeAllSessionsStatement;
    public static PreparedStatement aboutStatement;

    public static PreparedStatement userBySessionStatement;

    public static PreparedStatement changeNameStatement;
    public static PreparedStatement changeSurnameStatement;
    public static PreparedStatement changeDateOfBirthStatement;
    public static PreparedStatement changeIconStatement;

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
        isUserPasswordStatement = dbConnection.prepareStatement("SELECT COUNT(*)>0 FROM users WHERE username=? AND password_hash=?");
        UserInsertStatement = dbConnection.prepareStatement("INSERT INTO users (username, password_hash) VALUES (?, ?)");
        createSessionStatement = dbConnection.prepareStatement("INSERT INTO sessions (username, session) VALUES (?, ?)");
        removeSessionStatement = dbConnection.prepareStatement("DELETE FROM sessions WHERE session=?");
        removeAllSessionsStatement = dbConnection.prepareStatement("DELETE FROM sessions WHERE username=?");
        isActualSessionStatement = dbConnection.prepareStatement("SELECT COUNT(*)>0 FROM sessions WHERE session=?");
        aboutStatement = dbConnection.prepareStatement("SELECT username, name, surname, icon, dateOfBirth FROM users WHERE username=?");

        userBySessionStatement = dbConnection.prepareStatement("SELECT username FROM sessions WHERE session=?");

        changeNameStatement = dbConnection.prepareStatement("UPDATE users SET name=? WHERE username=?");
        changeSurnameStatement = dbConnection.prepareStatement("UPDATE users SET surname=? WHERE username=?");
        changeDateOfBirthStatement = dbConnection.prepareStatement("UPDATE users SET dateOfBirth=? WHERE username=?");
        changeIconStatement = dbConnection.prepareStatement("UPDATE users SET icon=? WHERE username=?");
    }
}
