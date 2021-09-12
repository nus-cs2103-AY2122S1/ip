package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Reads and converts user input to bhutu-understandable inputs.
 */
public class Parser {
    /**
     * Combines an array of strings into a space separated sentence.
     *
     * @param input the string array.
     * @return the sentence.
     */
    private StringBuilder combineInputArray(String... input) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < input.length; i++) {
            if (i < input.length - 1) {
                result.append(input[i]).append(" ");
            } else {
                result.append(input[i]);
            }
        }
        return result;
    }

    private String[] parseDeadline(StringBuilder result) throws DukeException {
        String date;
        String[] output = result.toString().split(" /by ");
        if (output.length < 2) {
            throw new DukeException("Please provide both description and date. Use '/by'. "
                    + "(eg. deadline submit project /by 2021-08-24)");
        }
        date = parseDate(output[1]);
        String[] parsed = new String[] {output[0], date};
        return parsed;
    }

    private String[] parseEvent(StringBuilder result) throws DukeException {
        String[] output = result.toString().split(" /at ");
        if (output.length < 2) {
            throw new DukeException("Please provide both description and time. Use '/at'. "
                    + "(eg. event fix hair /at 1pm)");
        }
        String[] parsed = new String[] {output[0], output[1]};
        return parsed;
    }

    private String[] parseTodo(StringBuilder result, String[] input) throws DukeException {
        if (input.length < 2) {
            throw new DukeException("Please specify the task you want to do");
        } else {
            String[] output =  new String[] {result.toString()};
            return output;
        }
    }

    private String[] parseDone(String[] input) throws DukeException {
        if (input.length < 2) {
            throw new DukeException("Please specify which task you have done");
        } else if (input.length != 2) {
            throw new DukeException("'done' command requires exactly 1 argument. (eg. done 12)");
        }
        try {
            Integer.parseInt(input[1]);
        } catch (Exception e) {
            throw new DukeException("'done' command requires an integer as number. (eg. done 12)");
        }

        String[] result =  new String[] {input[1]};
        return result;
    }

    private String[] parseList(String[] input) throws DukeException {
        if (input.length != 1) {
            throw new DukeException("'list' command doesn't require any arguments.");
        } else {
            String[] result = new String[] {input[0]};
            return result;
        }
    }

    private String[] parseBye(String[] input) throws DukeException {
        if (input.length != 1) {
            throw new DukeException("'bye' command doesn't require any arguments.");
        } else {
            String[] result = new String[] {input[0]};
            return result;
        }
    }

    private String[] parseDelete(String[] input) throws DukeException {
        if (input.length < 2) {
            throw new DukeException("Please specify which task you want to delete");
        } else if (input.length != 2) {
            throw new DukeException("'delete' command requires exactly 1 argument. (eg. done 12)");
        }
        try {
            Integer.parseInt(input[1]);
        } catch (Exception e) {
            throw new DukeException("'delete' command requires an integer as number. (eg. done 12)");
        }
        String[] result = new String[] {input[1]};
        return result;
    }

    /**
     * Converts the user input string into meaningful commands.
     *
     * @param input the user input string.
     * @return the meaningful commands.
     */
    public String[] compileInput(String... input) throws DukeException {
        StringBuilder result = combineInputArray(input);

        String commandEntered = input[0];
        assert commandEntered != null : "Command is NULL";

        switch (commandEntered) {
        case "deadline":
            return parseDeadline(result);
        case "event":
            return parseEvent(result);
        case "todo":
            return parseTodo(result, input);
        case "done":
            return parseDone(input);
        case "list":
            return parseList(input);
        case "bye":
            return parseBye(input);
        case "delete":
            return parseDelete(input);
        case "find":
            String[] parsedString = new String[] {input[1]};
            return parsedString;
        case "undo":
        case "help":
            return new String[] {commandEntered};
        default:
            throw new DukeException("I don't recognise this command\n"
                    + "Try 'list', 'todo', 'event', 'deadline', 'done', 'bye', 'help' or 'undo'.");
        }
    }

    /**
     * Parses a raw date string as input into a valid date and time string.
     * 
     * @param input The raw date string.
     * @return A string valid as a date.
     * @throws DukeException Thrown if the input is an invalid date.
     */
    private String parseDate(String input) throws DukeException {
        try {
            LocalDate date = LocalDate.parse(input);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException error) {
            throw new DukeException("Please enter a valid date in this format 'YYYY-MM-dd' "
                    + "(eg. 2021-10-07)");
        }
    }
}
