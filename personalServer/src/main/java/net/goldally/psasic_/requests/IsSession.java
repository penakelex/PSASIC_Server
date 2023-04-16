package net.goldally.psasic_.requests;

public class IsSession extends Request {
    private final String authKey;

    public IsSession(String authKey){
        this.authKey = authKey;
    }
}
