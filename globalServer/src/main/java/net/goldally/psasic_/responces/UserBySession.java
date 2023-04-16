package net.goldally.psasic_.responces;

import com.google.gson.JsonElement;

public class UserBySession extends Minimal {
    String username;

    public UserBySession(String username) {
        super(200, "OK");
        this.username = username;
    }
}
