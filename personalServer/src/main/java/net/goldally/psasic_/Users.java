package net.goldally.psasic_;

import net.goldally.psasic_.misc.GEO;
import net.goldally.psasic_.misc.Message;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Users {
    public static List<String> getAllUsers() throws SQLException {
        ResultSet a = DataBaseControl.listUsersStatement.executeQuery();
        List<String> usernames = new ArrayList<>();
        while (a.next()) {
            usernames.add(a.getString(1));
        }
        return usernames;
    }

    public static boolean isFriends(String userA, String userB) throws SQLException {
        if (Objects.equals(userA, userB)) {
            throw new RuntimeException("Самому себя нельзя отправить запрос в друзья.");
        }
        DataBaseControl.isFriendsStatement.setString(1, userA);
        DataBaseControl.isFriendsStatement.setString(2, userB);
        boolean a = DataBaseControl.isFriendsStatement.executeQuery().getBoolean(1);
        if (a) {
            return true;
        }
        DataBaseControl.isFriendsStatement.setString(1, userB);
        DataBaseControl.isFriendsStatement.setString(2, userA);
        return DataBaseControl.isFriendsStatement.executeQuery().getBoolean(1);
    }

    // Отправил ли пользователь A пользователю Б запрос в друзья
    private static boolean hasInvitation(String userA, String userB) throws SQLException {
        DataBaseControl.isSentInvite.setString(1, userA);
        DataBaseControl.isSentInvite.setString(2, userB);
        return DataBaseControl.isSentInvite.executeQuery().getBoolean(1);
    }

    public static int addFriend(String authKey, String username) throws IOException, SQLException {
        String sender = Sessions.userBySession(authKey);
        if (!isMember(username)) {
            return 2;
        }
        if (isFriends(sender, username)) {
            throw new RuntimeException("Вы уже друзья.");
        }
        if (hasInvitation(username, sender)) {
            DataBaseControl.setFriendStatement.setString(1, sender);
            DataBaseControl.setFriendStatement.setString(2, username);
            DataBaseControl.setFriendStatement.execute();
            DataBaseControl.removeInviteStatement.setString(1, sender);
            DataBaseControl.removeInviteStatement.setString(2, username);
            DataBaseControl.removeInviteStatement.execute();
            DataBaseControl.removeInviteStatement.setString(2, sender);
            DataBaseControl.removeInviteStatement.setString(1, username);
            DataBaseControl.removeInviteStatement.execute();
            return 1;
        }
        DataBaseControl.sendInviteStatement.execute();
        DataBaseControl.sendInviteStatement.setString(1, sender);
        DataBaseControl.sendInviteStatement.setString(2, username);
        return 0;
    }

    private static boolean isMember(String username) throws SQLException {
        DataBaseControl.isMemberStatement.setString(1, username);
        return DataBaseControl.isMemberStatement.executeQuery().getBoolean(1);
    }

    public static void removeFriend(String authKey, String username) throws IOException, SQLException {
        String sender = Sessions.userBySession(authKey);
        DataBaseControl.removeFriendStatement.setString(1, sender);
        DataBaseControl.removeFriendStatement.setString(2, username);
        DataBaseControl.removeFriendStatement.execute();
        DataBaseControl.removeFriendStatement.setString(2, sender);
        DataBaseControl.removeFriendStatement.setString(1, username);
        DataBaseControl.removeFriendStatement.execute();
    }

    public static List<String> getFriends(String authKey) throws IOException, SQLException {
        String sender = Sessions.userBySession(authKey);
        DataBaseControl.listFriendsStatement.setString(1, sender);
        ResultSet a = DataBaseControl.listFriendsStatement.executeQuery();
        List<String> usernames = new ArrayList<>();
        while (a.next()) {
            usernames.add(a.getString(1));
        }
        return usernames;
    }

    public static void setGeo(String authKey, double altitude, double longitude) throws IOException, SQLException {
        String sender = Sessions.userBySession(authKey);
        DataBaseControl.setGeoStatement.setDouble(1, altitude);
        DataBaseControl.setGeoStatement.setDouble(2, longitude);
        DataBaseControl.setGeoStatement.setString(2, sender);
    }

    public static GEO getGeo(String username) throws SQLException {
        DataBaseControl.getGeoStatement.setString(1, username);
        ResultSet a = DataBaseControl.getGeoStatement.executeQuery();
        return new GEO(a.getDouble(1), a.getDouble(2));
    }

    public static String sendMessage(String authKey, String username, String message) throws SQLException, IOException {
        String sender = Sessions.userBySession(authKey);
        if (!isFriends(sender, username)) {
            throw new IOException("Ошибка! Вы не в друзьях.");
        }
        DataBaseControl.sendMessageStatement.setString(1, sender);
        DataBaseControl.sendMessageStatement.setString(2, username);
        DataBaseControl.sendMessageStatement.setString(3, message);
        DataBaseControl.sendMessageStatement.execute();
        return message;
    }

    public static Object getMessages(String authKey, String username) throws IOException {
        String sender = Sessions.userBySession(authKey);
        // TODO: Реализовать список сообщений
        return new ArrayList<Message>();
    }

    public static Object getNewMessages(String authKey) throws IOException {
        String sender = Sessions.userBySession(authKey);
        // TODO: Реализовать список сообщений
        return new ArrayList<Message>();
    }
}
