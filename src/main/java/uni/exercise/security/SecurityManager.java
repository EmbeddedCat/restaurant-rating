package uni.exercise.security;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SecurityManager {

    public static String toHexString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));

        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    public static String getHash(String message) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return toHexString(md.digest(message.getBytes(StandardCharsets.UTF_8)));
    }

    public static int getTwoAuthCode() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[2];
        random.nextBytes(bytes);
        // Get the random code.
        return (int) (bytes[0] | bytes[1] << 8) & 0xEFFF;
    }

}
