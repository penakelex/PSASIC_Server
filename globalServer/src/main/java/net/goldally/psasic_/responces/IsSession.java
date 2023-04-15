package net.goldally.psasic_.responces;

public class IsSession extends Minimal {
    boolean state;
    public IsSession(boolean state) {
        super(200, "OK");
        this.state = state;
    }
}
