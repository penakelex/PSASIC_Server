package net.goldally.psasic_.misc;

import java.sql.Timestamp;

public class Message {
    String message;
    long date;

    Message(String message, long date) {
        this.message = message;
        this.date = date;
    }
}
