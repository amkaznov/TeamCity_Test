package com.example.teamcity.api.generators;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

public class RandomData {
    public static final int LENGTH=10;
    public static String getString(){
        return "test_" + RandomStringUtils.randomAlphabetic(LENGTH);
    }

    public static final int LENGTH_256=256;
    public static String getString256(){
        return RandomStringUtils.randomAlphabetic(LENGTH_256);
    }

    public static Integer getInt(){
        return Faker.instance().number().numberBetween(1,10000);
    }

}
