package duke.parser;

import java.util.ArrayList;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exception.DukeException;
import duke.exception.EmptyCommandException;
import duke.exception.IncorrectFormatException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDurationException;
import duke.exception.MessageEmptyException;

/**
 * Handles and interprets user commands for Duke.
 */
public class Parser {

    /** Represents the bye command word to call. */
    public final String BYE_COMMAND = "bye";

    /** Represents the deadline command word to call. */
    public final String DEADLINE_COMMAND = "deadline";

    /** Represents the delete command word to call. */
    public static final String DELETE_COMMAND = "delete";

    /** Represents the done command word to call. */
    public final String DONE_COMMAND = "done";

    /** Represents the event command word to call. */
    public final String EVENT_COMMAND = "event";

    /** Represents the find command word to call. */
    public final String FIND_COMMAND = "find";

    /** Represents the list command word to call. */
    public final String LIST_COMMAND = "list";

    /** Represents the todo command word to call. */
    public final String TODO_COMMAND = "todo";

    /** Represents an empty command word to call. */
    public static final String EMPTY_COMMAND = "";

    /**
     * Parses a string task list index to an integer.
     *
     * @param words Entire user input.
     * @return Index as an integer.
     * @throws NumberFormatException If the string cannot be converted into an integer.
     */
    private int parseIndex(String[] words) throws NumberFormatException, MessageEmptyException {
        try {
            String index = words[1];
            return Integer.parseInt(index) - 1;
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MessageEmptyException();
        }
    }

    /**
     * Parses relevant information from deadline.
     *
     * @param deadline user inputted deadline.
     * @return ArrayList of relevant information extracted from user input.
     * @throws MessageEmptyException if the user does not include the "/by" keyword.
     * @throws IncorrectFormatException if the user input contains an incorrect duration format.
     */
    private ArrayList<String> parseAddDeadline(String deadline) throws MessageEmptyException,
            IncorrectFormatException {

        ArrayList<String> parsedDeadline = new ArrayList<>();

        try {
            deadline = deadline.substring(9).trim();
        } catch (StringIndexOutOfBoundsException e) {
            throw new MessageEmptyException();
        }

        String[] result = deadline.split("/by");

        if (result.length == 0) {
            throw new MessageEmptyException();
        } else if (result.length == 1) {
            // throws an error if "/by" is not present in the message
            throw new IncorrectFormatException("deadline", "/by");
        } else if (result[0].trim().equals("")) {
            throw new MessageEmptyException();
        }

        assert result.length >= 2 : " Information missing in command";

        String description = result[0].trim();
        String by = result[1].trim();

        parsedDeadline.add(description);
        parsedDeadline.add(by);
        return parsedDeadline;
    }

    /**
     * Parses relevant information from event.
     *
     * @param event user inputted event.
     * @return ArrayList of relevant information extracted from user input.
     * @throws MessageEmptyException if the user input is lacking a message.
     * @throws IncorrectFormatException if the user input does not include the "/at" keyword.
     * @throws InvalidDurationException if the user input contains an incorrect duration format.
     */
    private ArrayList<String> parseAddEvent(String event) throws MessageEmptyException, IncorrectFormatException,
            InvalidDurationException {

        ArrayList<String> parsedEvent = new ArrayList<>();

        try {
            event = event.substring(6).trim();
        } catch (StringIndexOutOfBoundsException e) {
            throw new MessageEmptyException();
        }

        String[] result = event.split("/at");

        if (result.length == 0) {
            throw new MessageEmptyException();
        } else if (result.length == 1) {
            // throws an error if "/at" is not present in the message
            throw new IncorrectFormatException("event", "/at");
        } else if (result[0].trim().equals("")) {
            throw new MessageEmptyException();
        }

        assert result.length >= 2 : " Information missing in command";

        String description = result[0].trim();
        String at = result[1].trim();

        // throws error if it doesn't even contain sufficient number of characters for correct format
        if (at.replaceAll("\\s", "").length() < 19) { // YYYY/MM/DD HHMM - HHMM
            throw new InvalidDurationException();
        }

        String date = at.substring(0, 10).trim(); // at this point, date contains 10 chars YYYY/MM/DD
        String eventDuration = at.substring(11).trim();
        String[] eventTimes = eventDuration.split("-");

        // if no "-" present
        if (eventTimes.length != 2) {
            throw new InvalidDurationException();
        }

        parsedEvent.add(description);
        parsedEvent.add(date);
        parsedEvent.add(eventTimes[0]);
        parsedEvent.add(eventTimes[1]);
        return parsedEvent;
    }

    /**
     * Parses the user input for the todo command.
     *
     * @param todo entire user input.
     * @return the actual task to be added as a todo.
     * @throws MessageEmptyException if no message follows the todo command.
     */
    private String parseTodo(String todo) throws MessageEmptyException {
        try {
            return todo.substring(5).trim();
        } catch (StringIndexOutOfBoundsException e) {
            throw new MessageEmptyException();
        }
    }


    /**
     * Logic for handling different commands and executing the appropriate methods for the inputted command.
     * Throws appropriate exceptions for its respective error.
     *
     * @param input user input.
     * @return respective Command according to command word in user input.
     * @throws DukeException if any violations of commands in user input occurs.
     */
    public Command handleCommands(String input) throws DukeException {

        String[] words = parseInput(input);
        String command = getCommand(words);

        switch (command) {
        case BYE_COMMAND: // only applicable to GUI Duke
            return new ByeCommand();
        case DEADLINE_COMMAND:
            return new DeadlineCommand(parseAddDeadline(input));
        case DELETE_COMMAND:
            return new DeleteCommand(parseIndex(words));
        case DONE_COMMAND:
            return new DoneCommand(parseIndex(words));
        case EVENT_COMMAND:
            return new EventCommand(parseAddEvent(input));
        case FIND_COMMAND:
            return new FindCommand(words);
        case LIST_COMMAND:
            return new ListCommand();
        case TODO_COMMAND:
            return new TodoCommand(parseTodo(input));
        case EMPTY_COMMAND:
            throw new EmptyCommandException();
        default: // all other inputs that are not supported
            throw new InvalidCommandException();
        }
    }

    /**
     * Trims the user input and splits it by white spaces.
     *
     * @param input entire user input.
     * @return an array of Strings of the user input split by white spaces.
     */
    private String[] parseInput(String input) {
        String trimmedInput = input.trim();
        return trimmedInput.split(" ");
    }

    /**
     * Isolates the command word.
     *
     * @param parsedInput parsed user input.
     * @return command word for the relevant task.
     */
    private String getCommand(String[] parsedInput) {
        return parsedInput[0].toLowerCase();
    }
}
