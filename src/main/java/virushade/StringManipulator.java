package virushade;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A utility class that helps with string manipulation.
 */
public class StringManipulator {
    /**
     * Separates the string by the first instance of the given character.
     *
     * @param str The input string.
     * @param character The character to partition the string by.
     * @return The pair of string, separated by the partition character.
     */
    private static String[] partition(String str, char character) {
        int index = str.indexOf(character);

        String[] pair = new String[2];

        if (index > -1) {
            // Gets rid of a space in the end of the first partition.
            pair[0] = str.substring(0, index).trim();
            pair[1] = str.substring(index + 1).trim();
        } else {
            // If there exists no '/' in the string, set the tail of the pair as an empty string.
            pair[0] = str;
            pair[1] = "";
        }
        return pair;
    }

    /**
     * Partitions the string into 2, seperated by the first '/'.
     *
     * @param str The input string
     * @return The pair of strings.
     */
    public static String[] slashPartition(String str) {
        return partition(str, '/');
    }

    /**
     * Returns the first word and everything after the first ' ' of the String.
     *
     * @param input The input word.
     * @return The pair of strings
     */
    public static String[] wordSeparator(String input) {
        return partition(input, ' ');
    }

    /**
     * A function that returns everything after the first instance of '.'.
     *
     * @param str The input string.
     * @return The substring of str after the first '.'.
     */
    public static String everythingAfterDot(String str) {
        int index = str.indexOf('.');

        if (index > -1) {
            return str.substring(index + 1).trim();
        } else {
            return str;
        }
    }

    /**
     * Converts the input string from 24-hour time to 12-hour time representation.
     *
     * @param timeString The input string
     * @return The converted string.
     */
    private static String convertTime(String timeString) {
        try {
            int timeIn24hRepresentation = Integer.parseInt(timeString);
            int hours = timeIn24hRepresentation / 100;
            int minutes = timeIn24hRepresentation % 100;
            boolean isOutOfBounds = hours < 0 || hours > 24 || minutes < 0 || minutes > 60;
            boolean cannotConvertToTime = Integer.parseInt(timeString.substring(2, 3)) > 5;

            if (isOutOfBounds || cannotConvertToTime) {
                return timeString;
            } else {
                return convertTimeToStringRepresentation(hours, minutes);
            }
        } catch (NumberFormatException e) {
            return timeString;
        }
    }

    private static String convertTimeToStringRepresentation(int hours, int minutes) {
        assert hours > -1;
        assert hours < 24;
        assert minutes > -1;
        assert minutes < 60;

        if (hours > 11) {
            if (hours != 12) {
                hours -= 12;
            }

            if (hours < 10 && minutes < 10) {
                return "0" + hours + ":0" + minutes + "PM";
            } else if (hours < 10) {
                return "0" + hours + ":" + minutes + "PM";
            } else if (minutes < 10) {
                return hours + ":0" + minutes + "PM";
            } else {
                return hours + ":" + minutes + "PM";
            }
        } else {
            if (hours == 0) {
                hours += 12;
            }

            if (hours < 10 && minutes < 10) {
                return "0" + hours + ":0" + minutes + "AM";
            } else if (hours < 10) {
                return "0" + hours + ":" + minutes + "AM";
            } else if (minutes < 10) {
                return hours + ":0" + minutes + "AM";
            } else {
                return hours + ":" + minutes + "AM";
            }
        }
    }

    /**
     * Converts the inputString into DD MMM YYYY format, or 12-hour time representation if applicable.
     *
     * @param inputString The input string.
     * @return A string in DD MMM YYYY TIME format if inputString is in "YYYY-MM-DD TIME" format.
     */
    public static String convertDateAndTime(String inputString) {
        if (inputString.length() < 10) {
            return convertTime(inputString);
        }

        String dateString = inputString.trim().substring(0, 10).trim();
        String timeString = inputString.trim().substring(10).trim();
        try {
            String date = LocalDate.parse(dateString).format(DateTimeFormatter.ofPattern("d MMM yyyy"));

            if (timeString.length() == 4) {
                return date + " " + convertTime(timeString);
            }

            return date + timeString;
        } catch (DateTimeParseException e) {
            return inputString;
        }
    }
}
