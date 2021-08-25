package Duke.Parser;
//The code below was referenced from https://stackoverflow.com/questions/23488721/how-to-check-if-string-matches-date-pattern-using-time-api

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Date;

public class CustomDateFormatter {
    public final String[] patternsWithTime = new String[] {
            "dd-mm-yyyy HH:mm",
            "dd-mm-yyyy hh:mm:ss.s",
            "yyyy-mm-dd hh:mm",
            "yyyy-mm-dd hh:mm:ss.s",
            "MMM dd yyyy HH:mm",
            "dd MMM yyyy HH:mm",
    };
    public final String[] patternsWithoutTime = new String[] {
            "dd MMM yyyy",
            "dd-mm-yyyy",
            "dd-mm-yy",
            "dd-MM-yyyy",
            "dd mm yy",
            "dd mm yyyy",
            "dd MM yyyy",
            "dd/mm/yy",
            "dd/mm/yyyy",
            "dd/MM/yyyy",
            "dd.mm.yy",
            "dd.mm.yyyy",
            "dd.MM.yyyy",
            "dd/M/yyyy",
            "yyyy-mm-dd",
    };

    public static LocalDate getLocalDateFromString(String str) {
        CustomDateFormatter formatter = new CustomDateFormatter();
        return formatter.formatWithoutTime(str);
    }


    public LocalDateTime formatWithTime(String text) {
        for (String pattern: patternsWithTime) {
            try {
                return LocalDateTime.parse(text, DateTimeFormatter.ofPattern(pattern));
            } catch (DateTimeParseException e) {
                // Don't want to print anything here as it would disrupt the UI
            }
        }
        return null;
    }

    public LocalDate formatWithoutTime(String text) {
        for (String pattern: patternsWithoutTime) {
            try {
                return LocalDate.parse(text, DateTimeFormatter.ofPattern(pattern));
            } catch (DateTimeParseException e) {
                // Don't want to print anything here as it would disrupt the UI
            }
        }
        return null;
    }

    public static void main(String[] args) {
        CustomDateFormatter temp = new CustomDateFormatter();
        LocalDateTime dateTime = temp.formatWithTime("10 Oct 2014 01:00");
        LocalDate date = temp.formatWithoutTime("12-12-2019");

    }
}


