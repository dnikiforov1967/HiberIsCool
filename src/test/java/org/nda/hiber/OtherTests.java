package org.nda.hiber;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAccessor;


import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OtherTests {

    @Test
    void testOfDate() throws ParseException {
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                .append(ISO_LOCAL_DATE_TIME)
                .appendLiteral('Z')
                .toFormatter()
                .withZone(ZoneId.systemDefault());
        TemporalAccessor parse = dateTimeFormatter.parse("2025-04-07T23:22:21Z");
        Instant from = Instant.from(parse);
        System.out.println(Date.from(from));

        //parse = dateTimeFormatter.parse("04.07.2025");
        //System.out.println(LocalDate.from(parse));
    }



    @Test
    void testOfStackTraceSwallow() {
        Integer[] arr = {1};
        for(int i = 0; i<100; i++) {
            try {
                Integer value = arr[3];
                assertEquals(0, value);
            } catch(ArrayIndexOutOfBoundsException e) {
                e.printStackTrace(System.out);
            }
        }
    }



}
