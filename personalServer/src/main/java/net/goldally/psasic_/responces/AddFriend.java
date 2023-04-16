package net.goldally.psasic_.responces;

import com.google.gson.JsonElement;

public class AddFriend extends Minimal {

    int state;
    public AddFriend(int state) {
        super(200, "OK");
        this.state = state;
    }
}
