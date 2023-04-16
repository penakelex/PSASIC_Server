package net.goldally.psasic_.responces;

import com.google.gson.JsonElement;

public class GetMessages extends Minimal {
    public GetMessages(Object messages) {
        super(200, "OK");
    }
}
