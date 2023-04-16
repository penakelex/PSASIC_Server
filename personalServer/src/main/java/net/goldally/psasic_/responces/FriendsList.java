package net.goldally.psasic_.responces;

import com.google.gson.JsonElement;

import java.util.List;

public class FriendsList {
    private final List<String> usernames;

    public FriendsList(List<String> usernames){
        this.usernames = usernames;
    }
}
