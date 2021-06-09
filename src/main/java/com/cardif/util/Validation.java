package com.cardif.util;

import java.util.List;

public class Validation {

    public static boolean isZero(int value) {
        return value <= 0;
    }

    public static boolean containsZero(List<Integer> values) {
        return values.contains(0);
    }

}
