package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    /**
     * combine an array of strings into a space seperated sentence.
     * @param input the string array.
     * @return the sentence.
     */
    private StringBuilder combineInputArray(String[] input) {
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

    /**
     * Convert the user input string into meaningful commands.
     * @param input the user input string.
     * @return the meaningful commands.
     */
    public String[] compileInput(String[] input) throws DukeException {
        StringBuilder result = combineInputArray(input);
        String date;
        switch (input[0]) {
        case "deadline":
            String[] output = result.toString().split(" /by ");
            if (output.length < 2) {
                throw new DukeException("Please provide both description and date. Use '/by'. "
                        + "(eg. deadline submit project /by 2021-08-24)");
            }
            date = parseDate(output[1]);
            return new String[] {output[0], date};
        case "event":
            String[] output1 = result.toString().split(" /at ");
            if (output1.length < 2) {

                throw new DukeException("Please provide both description and time. Use '/at'. "
                        + "(eg. event fix hair /at 1pm)");
            }
            return new String[] {output1[0], output1[1]};
        case "todo":
            if (input.length < 2) {
                throw new DukeException("Please specify the task you want to do");
            } else {
                return new String[] {result.toString()};
            }
        case "done":
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

            return new String[] {input[1]};
        case "list":
            if (input.length != 1) {
                throw new DukeException("'list' command doesn't require any arguments.");
            } else {
                return new String[] {input[0]};
            }
        case "bye":
            if (input.length != 1) {
                throw new DukeException("'bye' command doesn't require any arguments.");
            } else {
                return new String[] {input[0]};
            }
        case "delete":
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

            return new String[] {input[1]};

        default:
            throw new DukeException("I don't recognise this command\n"
                    + "Try 'list', 'todo', 'event', 'deadline', 'done' or 'bye'");
        }
    }

    /**
     * Parses a raw date string as input into a valid date and time string.
     * @param input The raw date string
     * @return A string valid as a date
     * @throws DukeException Thrown if the input is an invalid date
     */
    private String parseDate(String input) throws DukeException {
        try {
            LocalDate date = LocalDate.parse(input);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException error) {
            throw new DukeException("Please enter a valid date in this format 'YYYY-MM-dd'");
        }
    }
}
