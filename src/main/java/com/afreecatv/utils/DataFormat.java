package main.java.com.afreecatv.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataFormat {
    public String getToday() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String nowFormatted = now.format(formatter);
        return nowFormatted;

    }
}
