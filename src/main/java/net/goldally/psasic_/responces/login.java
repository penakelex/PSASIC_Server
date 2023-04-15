package net.goldally.psasic_.responces;

public class login extends minimal{
    String authKey;
    public login(int code, String authKey) {
        super(code, "OK");
        this.authKey = authKey;
    }
}
