package duke.parser;

// The code below was referenced from
// https://stackoverflow.com/questions/23488721/how-to-check-if-string-matches-date-pattern-using-time-api

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class helps to format some common possible String inputs for dates
 * to parse them as LocalDate objects.
 */
public class CustomDateFormatter {

    /** The array of common patterns with time. */
    private final String[] patternsWithTime = new String[] {
        "dd-mm-yyyy HH:mm",
        "dd-mm-yyyy hh:mm:ss.s",
        "yyyy-mm-dd hh:mm",
        "yyyy-mm-dd hh:mm:ss.s",
        "MMM dd yyyy HH:mm",
        "dd MMM yyyy HH:mm",
    };

    /** The array of common patterns without time. */
    private final String[] patternsWithoutTime = new String[] {
        "dd/MM/yyyy",
        "dd/mm/yyyy",
        "dd MMM yyyy",
        "dd-mm-yyyy",
        "dd-mm-yy",
        "dd-MM-yyyy",
        "dd mm yy",
        "dd mm yyyy",
        "dd MM yyyy",
        "dd/mm/yy",
        "dd.mm.yy",
        "dd.mm.yyyy",
        "dd.MM.yyyy",
        "dd/M/yyyy",
        "yyyy-mm-dd",
    };

    /**
     * This method helps to get the LocalDate object from a String.
     *
     * @param str The string to be formatted
     * @return
     */
    public static LocalDate getLocalDateFromString(String str) {
        CustomDateFormatter formatter = new CustomDateFormatter();
        return formatter.formatWithoutTime(str);
    }

    /**
     * This method helps to get the LocalDateTime object from a String.
     *
     * @param text The string to be formatted
     * @return
     */
    public LocalDateTime formatWithTime(String text) {
        for (String pattern: patternsWithTime) {
            try {
                return LocalDateTime.parse(text, DateTimeFormatter.ofPattern(pattern));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date time exception caught!");
            }
        }
        return null;
    }

    /**
     * This method helps to get the LocalDate object from a String.
     *
     * @param text The string to be formatted
     * @return
     */
    public LocalDate formatWithoutTime(String text) {
        for (String pattern: patternsWithoutTime) {
            try {
                return LocalDate.parse(text, DateTimeFormatter.ofPattern(pattern));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date time exception caught!");
            }
        }
        return null;
    }

}


