package net.goldally.psasic_.misc;

import java.io.UnsupportedEncodingException;
import java.security.*;

public class Hasher {
    private static MessageDigest md;

    static {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String hashed(String str) throws UnsupportedEncodingException {
        byte[] bytesOfMessage = str.getBytes("UTF-8");
        byte[] hash = md.digest(bytesOfMessage);
        return hash.toString();
    }
}
