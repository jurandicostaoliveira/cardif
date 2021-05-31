package com.cardif.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class Calculator {

    public static int currentAge(Date value) {
        String s = new SimpleDateFormat("yyyy-MM-dd").format(value);
        return Period.between(
                LocalDate.parse(s), LocalDate.now()
        ).getYears();
    }

}
