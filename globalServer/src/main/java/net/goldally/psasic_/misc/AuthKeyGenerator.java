package net.goldally.psasic_.misc;

import java.io.UnsupportedEncodingException;

public class AuthKeyGenerator {
    public static String generateAuthKey() throws UnsupportedEncodingException {
        return Hasher.hashed("" + System.currentTimeMillis() + "salt");
    }
}
