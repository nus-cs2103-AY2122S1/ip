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
     * @param str The input string
     * @return The pair of strings.
     */
    public static String[] slashPartition(String str) {
        return partition(str, '/');
    }

    /**
     * Returns the first word and everything after the first ' ' of the String.
     * @param input The input word.
     * @return The pair of strings
     */
    public static String[] wordSeparator(String input) {
        return partition(input, ' ');
    }

    /**
     * Returns everything after the first instance of a period.
     * @param str The input string.
     * @return The string after the first period.
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
     * @param timeString The input string
     * @return The converted string.
     */
    private static String convertTime(String timeString) {
        try {
            int timeIn24hRepresentation = Integer.parseInt(timeString);
            int hours = timeIn24hRepresentation / 100;
            int minutes = timeIn24hRepresentation % 100;
            boolean isOutOfBounds = hours < 0 || hours > 24 || minutes < 0 || minutes > 60;
        //    boolean cannotConvertToTime = Integer.parseInt(timeString.substring(2, 3)) > 5;
            boolean cannotConvertToTime = timeString.length() != 4;

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
     * @param inputString The input string.
     * @return A string in DD MMM YYYY TIME format if inputString is in "YYYY-MM-DD TIME" format.
     */
    public static String convertDateAndTime(String inputString) {
        String trimmedInputString = inputString.trim();

        if (trimmedInputString.length() < 10) {
            return convertTime(inputString);
        } else {
            String dateString = trimmedInputString.substring(0, 10).trim();
            String timeString = trimmedInputString.substring(10).trim();
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

    /**
     * Compares two string by their ASCII values, and returns either 0, -1, or 1.
     * @param firstString The first string.
     * @param secondString The second string.
     * @return 0 if both strings are of the same value.
     *         1 if the second string is considered larger than the first.
     *         -1 if the second string is considered smaller than the first.
     */
    public static int compareString(String firstString, String secondString) {
        if (isEmptyString(firstString) || isEmptyString(secondString)) {
            if (isEmptyString(firstString) && isEmptyString(secondString)) {
                return 0;
            } else if (isEmptyString(firstString)) {
                return 1;
            } else if (isEmptyString(secondString)) {
                return -1;
            }
        }

        char charOfFirst = firstString.charAt(0);
        char charOfSecond = secondString.charAt(0);
        int result = compareChar(charOfFirst, charOfSecond);

        if (result == 0) {
            return compareString(firstString.substring(1), secondString.substring(1));
        }

        return result;
    }

    /**
     * Compares two characters by their ASCII values, and returns either 0, -1 or 1.
     * @param firstChar The first character
     * @param secondChar The second character
     * @return 0 if both characters have the same value.
     *         1 if the second character has a larger value.
     *         -1 if the second character has a smaller value.
     */
    public static int compareChar(char firstChar, char secondChar) {
        return Character.compare(secondChar, firstChar);
    }

    private static boolean isEmptyString(String string) {
        return string.equals("");
    }
}
