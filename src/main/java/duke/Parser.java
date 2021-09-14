package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Represents a Parser that parses user input and returns corresponding command.
 */
public class Parser {

    /**
     * Parses user input and returns corresponding command.
     *
     * @param input User input in String.
     * @return Parsed command.
     * @throws DukeException If target task index not provided for deleting or marking task as done,
     * if invalid action command is provided or if date provided is in an invalid format.
     */
    public static Command parse(String input) throws DukeException {
        String[] inputList = input.split(" ");
        String action = inputList[0];
        try {
            switch (action) {
            case "list":
                return new ListCommand();
            case "delete":
                if (inputList.length != 2) {
                    throw new DukeException("Please provide the target task index!");
                }
                try {
                    return new DeleteCommand(Integer.parseInt(inputList[1]));
                } catch (NumberFormatException e) {
                    throw new DukeException("Please provide a valid target task index integer!");
                }
            case "done":
                if (inputList.length != 2) {
                    throw new DukeException("Please provide the target task index!");
                }
                try {
                    return new SetDoneCommand(Integer.parseInt(inputList[1]) - 1);
                } catch (NumberFormatException e) {
                    throw new DukeException("Please provide a valid target task index integer!");
                }
            case "todo":
                return new AddCommand("todo",
                        input.replaceFirst(Pattern.quote("todo"), "").trim(),
                        null);
            case "deadline":
                String[] deadlineInfo = input.replaceFirst(Pattern.quote("deadline"),
                        "").split("/by", 2);
                return new AddCommand("deadline",
                        deadlineInfo[0].trim(),
                        LocalDate.parse(deadlineInfo[1].trim()));
            case "event":
                String[] eventInfo = input.replaceFirst(Pattern.quote("event"),
                        "").split("/at", 2);
                return new AddCommand("event",
                        eventInfo[0].trim(),
                        LocalDate.parse(eventInfo[1].trim()));
            case "occurring":
                return new OccurringCommand(LocalDate.parse(inputList[1]));
            case "find":
                return new FindCommand(String.join(" ",
                        Arrays.copyOfRange(inputList, 1, inputList.length)));
            case "bye":
                return new ExitCommand();
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Date provided should be in YYYY-MM-DD format.");
        }
    }
}
