package duke.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * duke.util.Parser class that contains static methods to parse raw text.
 */
public class Parser {

    /**
     * Parses raw user input. Returns a Map of keywords to values, where the '/' character is
     * parsed as a keyword.
     *
     * @param text The user input to be parsed
     * @return Map of string to string, of keyword to value pairs.
     */
    public static Map<String, String> parseCommand(String text) {
        HashMap<String, String> inputMap = new HashMap<>();
        String[] splitted = text.split("\\s");

        String key = splitted[0];
        StringBuilder arguments = new StringBuilder();
        for (int i = 1;
             i < splitted.length;
             i++) {
            if (splitted[i].startsWith("/") && ValidParams.isValid(splitted[i])) {
                if (arguments.length() > 0) {
                    arguments.deleteCharAt(arguments.length() - 1);
                    inputMap.put(key,
                            arguments.toString());
                    arguments.setLength(0);
                } else {
                    inputMap.put(key,
                            null);
                }
                key = splitted[i];
            } else {
                arguments.append(String.format("%s ",
                        splitted[i]));
            }
        }
        if (arguments.length() > 0) {
            arguments.deleteCharAt(arguments.length() - 1);
            inputMap.put(key,
                    arguments.toString());
        } else {
            if (!inputMap.containsKey(key)) {
                inputMap.put(key,
                        null);
            }
        }
        return inputMap;
    }

    /**
     * Tries to parse an int.
     *
     * @param text A supposed numeric string.
     * @return Optional of the parsed string. If the string is erroneous,
     * returns an empty optional.
     */
    public static Optional<Integer> parseInt(String text) {
        try {
            return Optional.of(Integer.parseInt(text));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }


    /**
     * Ensures only the right commands are parsed with ValidParams enum.
     */
    public enum ValidParams {
        AT("/at"), BY("/by");

        private final String value;

        ValidParams(String value) {
            this.value = value;
        }

        public static boolean isValid(String test) {
            return Arrays.stream(ValidParams.values())
                    .anyMatch(x -> x.value.equals(test));
        }
    }

    public static Optional<LocalDateTime> parseDateTime(String dateTime) throws DukeException {
        return Parser.parseDateTime(dateTime, new DukeConfig());
    }


    public static Optional<LocalDateTime> parseDateTime(String dateTime, DukeConfig config) throws DukeException{
        String dateConfig = config.getDateConfig();
        String[] timeSplit = dateTime.split("\\s");
        String[] dateSplit = timeSplit[0].split("/");

        if (dateSplit.length != 3) {
            throw new DukeException("Invalid date format. The current specified format is " + dateConfig);
        }

        int date, month, year, hour, minute;
        date = parseInt(dateSplit[0]).filter(x -> x < 32).orElseThrow(() -> new DukeException("Invalid day"));
        month = parseInt(dateSplit[1]).filter(x -> x < 13).orElseThrow(() -> new DukeException("Invalid month"));
        year = parseInt(dateSplit[2]).orElseThrow(() -> new DukeException("Invalid year"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate time = LocalDate.parse(String.format("%s/%s/%s", year, month < 10 ? "0" + month : month, date),
                formatter);

        if (time.getDayOfMonth() != date) {
            throw new DukeException("The specified day of the month is not valid");
        }

        if (timeSplit.length == 2) {
            if (timeSplit[1].length() != 4) {
                throw new DukeException("Invalid time");
            }
            hour = parseInt(timeSplit[1].substring(0, 2)).filter(x -> x < 25).orElseThrow(() -> new DukeException(
                    "Invalid hour " +
                    "specified"));
            minute = parseInt(timeSplit[1].substring(2,4)).filter(x -> x < 60).orElseThrow(() -> new DukeException(
                    "Invalid " +
                    "minute " +
                    "specified"));
            return Optional.of(LocalDateTime.of(year,month,date,hour,minute));
        } else {
            return Optional.of(LocalDateTime.of(year, month, date, 0, 0));
        }

    }
}
