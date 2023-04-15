package net.goldally.psasic_.responces;

public class registration extends minimal{
    String authKey;
    public registration(int code, String authKey) {
        super(code, "OK");
        this.authKey = authKey;
    }
}
