package net.goldally.psasic_.misc;

import net.goldally.psasic_.PsasicMain;

public class Formater {
    public static String sampleResponse(int code, String message) {
        PsasicMain.log.info("{status: " + code + ", message: \"" + message + "\"}");
        return "{status: " + code + ", message: \"" + message + "\"}";
    }
}
