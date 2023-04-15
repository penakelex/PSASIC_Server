package net.goldally.psasic_.responces;

public class login extends Minimal {
    String authKey;
    public login(String authKey) {
        super(200, "OK");
        this.authKey = authKey;
    }
}
