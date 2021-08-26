import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class StringManipulator {
    /**
     * Partitions the string into 2, seperated by the first '/'.
     * @param str The input string
     * @return The pair of strings.
     */
    public static String[] slashPartition(String str) {
        int index = str.indexOf('/');

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

            if (timeIn24hRepresentation < 0 || timeIn24hRepresentation > 2359
                    || Integer.parseInt(timeString.substring(2,3)) > 5) {
                return timeString;
            } else {
                int hours = timeIn24hRepresentation/100;
                int minutes = timeIn24hRepresentation%100;

                if (hours > 12) {
                    hours -= 12;
                    if (hours < 10 && minutes < 10) {
                        return "0" + hours + ":0" + minutes + "PM";
                    } else if (hours < 10) {
                        return "0" + hours + ":" + minutes + "PM";
                    } else if (minutes < 10) {
                        return hours + ":" + minutes + "PM";
                    } else {
                        return hours + ":" + minutes + "PM";
                    }
                } else {
                    if (hours < 10 && minutes < 10) {
                        return "0" + hours + ":0" + minutes + "AM";
                    } else if (hours < 10) {
                        return "0" + hours + ":" + minutes + "AM";
                    } else if (minutes < 10) {
                        return hours + ":" + minutes + "AM";
                    } else {
                        return hours + ":" + minutes + "AM";
                    }
                }
            }
        } catch (NumberFormatException e) {
            return timeString;
        }
    }

    /**
     * Converts the inputString into DD MMM YYYY format, or 12-hour time representation if applicable.
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
