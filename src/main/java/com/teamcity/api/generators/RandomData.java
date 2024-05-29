package com.teamcity.api.generators;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

public class RandomData {
    public static final int LENGTH = 10;
    private static final String TEST_PREFIX = "test_";

    private RandomData() {
    }

    public static String getString() {
        return TEST_PREFIX + RandomStringUtils.randomAlphabetic(LENGTH);
    }

    public static String getString(int length) {
        // Генерируем строку кастомной длины, учитывая то, что test_ уже занимает 5 символов. Строка меньше 10 символов не может получиться
        return TEST_PREFIX + RandomStringUtils.randomAlphabetic(Math.max(length - TEST_PREFIX.length(), LENGTH));
    }

    public static final int LENGTH_256 = 256;

    public static String getString256() {
        return RandomStringUtils.randomAlphabetic(LENGTH_256);
    }

    public static Integer getInt() {
        return Faker.instance().number().numberBetween(1, 10000);
    }

}
