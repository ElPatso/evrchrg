package com.evrecharge;

import com.evrecharge.util.DateTimeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws ParseException {
        String text = "04/08/2019 12:55 AM";
        System.out.println(LocalDateTime.parse(text, DateTimeUtil.DATE_TIME_FORMATTER));
    }
}
