package com.evrecharge;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws ParseException {
        for (int i = 0; i < 15; i++){
            if (i==2){
                i = 5;
            }
            System.out.println(i);
        }


    }
}
