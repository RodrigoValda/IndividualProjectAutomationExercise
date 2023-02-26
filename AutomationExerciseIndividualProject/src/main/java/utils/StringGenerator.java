package utils;

import java.util.concurrent.ThreadLocalRandom;

public class StringGenerator {
    public static int randomNumber() {
        return ThreadLocalRandom.current().nextInt(7, 10 + 1);
    }

    public static String randomString() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String stringGenerated = "";
        for (int x = 0; x < 10; x++) {
            int index = randomNumber();
            char randomCharacter = characters.charAt(index);
            stringGenerated += randomCharacter;
        }
        return stringGenerated;
    }
    public static String randomEmailString() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String stringGenerated = "";
        for (int x = 0; x < 10; x++) {
            int index = randomNumber();
            char randomCharacter = characters.charAt(index);
            stringGenerated += randomCharacter;
        }
        return stringGenerated + "@xyc.com";
    }
}
