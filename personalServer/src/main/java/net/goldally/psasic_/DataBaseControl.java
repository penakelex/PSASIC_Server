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
    public static PreparedStatement isMemberStatement;
    public static PreparedStatement addMemberStatement;


    public static PreparedStatement isFriendsStatement;
    public static PreparedStatement isSentInvite;
    public static PreparedStatement setFriendStatement;
    public static PreparedStatement removeInviteStatement;
    public static PreparedStatement sendInviteStatement;
    public static PreparedStatement removeFriendStatement;
    public static PreparedStatement listFriendsStatement;
    public static PreparedStatement setGeoStatement;
    public static PreparedStatement getGeoStatement;

    //TODO: Сообщения
    public static PreparedStatement sendMessageStatement;


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
        listUsersStatement = dbConnection.prepareStatement("SELECT * FROM users");
        isMemberStatement = dbConnection.prepareStatement("SELECT COUNT(*)>0 FROM users WHERE username=?");
        addMemberStatement = dbConnection.prepareStatement("INSERT INTO users (username) values (?)");

        isFriendsStatement = dbConnection.prepareStatement("SELECT COUNT(*)>0 FROM friends WHERE friend1=? AND friend2=?");
        isSentInvite = dbConnection.prepareStatement("SELECT COUNT(*)>0 FROM invites WHERE sender=? AND reciver=?");
        setFriendStatement = dbConnection.prepareStatement("INSERT INTO friends (friend1, friend2) VALUES (?, ?)");
        removeInviteStatement = dbConnection.prepareStatement("DELETE FROM invites WHERE sender=? AND reciver=?");
        sendInviteStatement = dbConnection.prepareStatement("INSERT INTO invites (sender, reciver) VALUES (?, ?)");
        removeFriendStatement = dbConnection.prepareStatement("DELETE FROM friends WHERE friend1=? AND friend2=?");
        listFriendsStatement = dbConnection.prepareStatement("SELECT friend2 FROM friends WHERE friend1=?");
        setGeoStatement = dbConnection.prepareStatement("UPDATE geo SET latitude=?, longitude=? WHERE user=?");
        getGeoStatement = dbConnection.prepareStatement("SELECT latitude, longitude FROM geo WHERE user=?");
    }
}
