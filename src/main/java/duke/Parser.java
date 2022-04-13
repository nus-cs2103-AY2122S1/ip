package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    /**
     * Parses the input and splits it into command and parameter.
     * @param input Input string to be parsed.
     * @return String array of command and parameter.
     */
    public static String[] inputParser(String input) {
        String[] processedInputs = input.split(" ", 2);
        if (processedInputs.length != 2) {
            processedInputs = new String[]{processedInputs[0], ""};
        }
        return processedInputs;
    }

    /**
     * Parses a command and returns the enum equivalent.
     * @param input String of command to be parsed.
     * @return Enum equivalent of given command.
     */
    public static Command parseCommand(String input) {
        for (Command c : Command.values()) {
            if (c.strIsSelf(input)) {
                return c;
            }
        }
        return Command.UNKNOWN;
    }

    /**
     * Parses date into desired format if possible.
     * @param date Provided date string.
     * @return Formatted date (if possible)
     */
    public static String parseDate(String date) {
        String parsedDate = "";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            parsedDate = LocalDate.parse(date, formatter).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            parsedDate = "";
        }
        return parsedDate;
    }

    /**
     * Parses parameters containing dates into description and date.
     *
     * @param c Command to be parsed.
     * @param parameter Parameter containing description and date.
     * @return String array containing description and date.
     */

    public static String[] dateParameterParser(Command c, String parameter) {
        switch (c) {
        case DEADLINE :
            return parameter.split(" /by ");
        case EVENT:
            return parameter.split(" /at ");
        default:
            throw new DukeException("Invalid command for date parsing.");

        }
    }

}