package net.goldally.psasic_.requests;

import net.goldally.psasic_.PsasicMain;

public class Request {
    public String ToString() {
        return PsasicMain.gson.toJson(this);
    }
}
