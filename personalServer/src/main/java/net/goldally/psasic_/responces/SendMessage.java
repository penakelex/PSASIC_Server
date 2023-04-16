package net.goldally.psasic_.responces;

import com.google.gson.JsonElement;

public class SendMessage extends Minimal {
    String newMessage;

    public SendMessage(String sendMessage) {
        super(200, "OK");
        this.newMessage = sendMessage;
    }
}
