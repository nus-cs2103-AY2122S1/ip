package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Represents a Parser that parses user input and returns corresponding command.
 */
public class Parser {
    private static Action parseAction(String actionString) throws DukeException {
        try {
            return Action.valueOf(actionString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
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
        try {
            switch (parseAction(inputList[0])) {
            case LIST:
                return new ListCommand();
            case DELETE:
                return new DeleteCommand(getTaskIndex(inputList));
            case DONE:
                return new SetDoneCommand(getTaskIndex(inputList));
            case TODO:
                return new AddCommand("todo",
                        getTodoDescription(input),
                        null);
            case DEADLINE:
                return new AddCommand("deadline",
                        getDeadlineDescription(input),
                        getDeadlineDate(input));
            case EVENT:
                return new AddCommand("event",
                        getEventDescription(input),
                        getEventDate(input));
            case OCCURRING:
                return new OccurringCommand(getQueryDate(inputList));
            case FIND:
                return new FindCommand(getQuery(inputList));
            case BYE:
                return new ExitCommand();
            case UNDO:
                return new UndoCommand();
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Date provided should be in YYYY-MM-DD format.");
        }
    }

    private static int getTaskIndex(String[] inputList) throws DukeException {
        if (inputList.length != 2) {
            throw new DukeException("Please provide the target task index!");
        }
        return Integer.parseInt(inputList[1]) - 1;
    }

    private static String getTodoDescription(String input) {
        return input.replaceFirst(Pattern.quote("todo"), "").trim();
    }

    private static LocalDate getQueryDate(String[] inputList) {
        return LocalDate.parse(inputList[1]);
    }

    private static String getQuery(String[] inputList) {
        return String.join(" ",
                Arrays.copyOfRange(inputList, 1, inputList.length));
    }

    private static String getEventDescription(String input) {
        String[] eventInfo = extractEventInfo(input);
        return eventInfo[0].trim();
    }

    private static LocalDate getEventDate(String input) {
        String[] eventInfo = extractEventInfo(input);
        return LocalDate.parse(eventInfo[1].trim());
    }

    private static String getDeadlineDescription(String input) {
        String[] deadlineInfo = extractDeadlineInfo(input);
        return deadlineInfo[0].trim();
    }

    private static LocalDate getDeadlineDate(String input) {
        String[] deadlineInfo = extractDeadlineInfo(input);
        return LocalDate.parse(deadlineInfo[1].trim());
    }

    private static String[] extractEventInfo(String input) {
        return input.replaceFirst(Pattern.quote("event"), "").split("/at", 2);
    }

    private static String[] extractDeadlineInfo(String input) {
        return input.replaceFirst(Pattern.quote("deadline"), "").split("/by", 2);

    }
}
