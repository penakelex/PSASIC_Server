package net.goldally.psasic_.misc;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.HexFormat;

public class Hasher {
    private static MessageDigest md;

    static {
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String hashed(String str) throws UnsupportedEncodingException {
        byte[] bytesOfMessage = str.getBytes(StandardCharsets.UTF_8);
        byte[] hash = md.digest(bytesOfMessage);
        return HexFormat.of().formatHex(hash);
    }
}
