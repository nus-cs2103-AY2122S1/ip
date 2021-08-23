package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static String[] inputParser(String input) {
        String[] processed = input.split(" ", 2);
        if (processed.length != 2) {
            processed = new String[]{processed[0], ""};
        }
        return processed;
    }


    public static Command commandParser(String input) {
        for (Command c : Command.values()) {
            if (c.value.equals(input)) {
                return c;
            }
        }
        return Command.UNKNOWN;
    }

    public static String dateParser(String date) {
        String parsedDate = "";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            parsedDate = LocalDate.parse(date, formatter).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            parsedDate = "";
        }
        return parsedDate;
    }

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