package net.goldally.psasic_.responces;

public class Registration extends Minimal {
    String authKey;
    public Registration(String authKey) {
        super(200, "OK");
        this.authKey = authKey;
    }
}
