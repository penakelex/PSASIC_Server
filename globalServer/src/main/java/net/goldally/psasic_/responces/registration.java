package net.goldally.psasic_.responces;

public class registration extends Minimal {
    String authKey;
    public registration(String authKey) {
        super(200, "OK");
        this.authKey = authKey;
    }
}
