package uni.exercise.servlets.security;


import java.security.SecureRandom;


public class TwoFactorAuth {
    public static int getCode() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[2];
        // Get the random code.
        return (int) (bytes[0] | bytes[1] << 8) & 0xEFFF;
    }
}