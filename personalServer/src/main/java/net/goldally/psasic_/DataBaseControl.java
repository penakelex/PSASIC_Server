package net.goldally.psasic_;

import java.io.IOException;
import java.sql.*;

import static net.goldally.psasic_.misc.Reader.readFile;

public class DataBaseControl {
    public static Connection dbConnection;

    public static PreparedStatement createFriendsTableStatement;
    public static PreparedStatement createInvitesTableStatement;
    public static PreparedStatement createGeoTableStatement;
    public static PreparedStatement createMessagesTableStatement;
    public static PreparedStatement createUsersTableStatement;
    public static PreparedStatement listUsersStatement;


    public static void init() throws SQLException, ClassNotFoundException, IOException {
        // Подключение к базе данных.
        Class.forName("org.sqlite.JDBC");
        dbConnection = DriverManager.getConnection("jdbc:sqlite:PERSONAL.db");

        // Создание базы данных.
        createFriendsTableStatement = dbConnection.prepareStatement(readFile("sql/personal/createFriendsTable.sql"));
        createInvitesTableStatement = dbConnection.prepareStatement(readFile("sql/personal/createInvitesTable.sql"));
        createGeoTableStatement = dbConnection.prepareStatement(readFile("sql/personal/createGeoTable.sql"));
        createMessagesTableStatement = dbConnection.prepareStatement(readFile("sql/personal/createMessagesTable.sql"));
        createUsersTableStatement = dbConnection.prepareStatement(readFile("sql/personal/createUsersTable.sql"));
        //TODO: Сделать местную авторизацию, что-бы не использовать сразу везде один ключ.
        createFriendsTableStatement.execute();
        createInvitesTableStatement.execute();
        createGeoTableStatement.execute();
        createMessagesTableStatement.execute();
        createUsersTableStatement.execute();

        // Заполнение шаблонов запросов к БД.
        createMessagesTableStatement = dbConnection.prepareStatement("Select username from users");
    }
}
