package com.evrecharge;

import com.atlis.location.model.impl.MapPoint;
import com.atlis.location.nominatim.NominatimAPI;
import com.evrecharge.util.DateTimeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws ParseException {
        String city = "Lviv";
        String street = "Luhanska";
        String build = "20";
        String endpointUrl = "https://nominatim.openstreetmap.org/";
        MapPoint mapPoint = NominatimAPI.with(endpointUrl).getMapPointFromAddress(build, street, city, city, "Ukraine", 5);
        System.out.println(mapPoint.getLatitude());
        System.out.println(mapPoint.getLongitude());
    }
}
