package ru.itis.util;

import java.util.concurrent.ThreadLocalRandom;

public final class StringUtil {

    private static final String CHARACTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int MIN_LENGTH = 40;
    private static final int MAX_LENGTH = 80;

    private StringUtil() {
    }

    public static String generateRandomString() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int length = random.nextInt(MIN_LENGTH, MAX_LENGTH + 1);
        StringBuilder result = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            result.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        return result.toString();
    }
}
