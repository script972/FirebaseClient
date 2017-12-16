package com.script972.testex.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by script972 on 16.12.2017.
 */

public class EmailRegular {
    private Pattern pattern;
    private Matcher matcher;

    private final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailRegular() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public boolean validate(final String hex) {
        matcher = pattern.matcher(hex);

        return matcher.matches();
    }
}
