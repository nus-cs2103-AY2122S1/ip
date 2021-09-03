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

        String description = result[0].trim(); // trims the additional spaces to the left and right of "by"
        String by = result[1].trim(); // trims the additional spaces to the left and right of "by"
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
        String description = result[0].trim(); // trims the additional spaces to the left and right of "at"
        String at = result[1].trim(); // trims the additional spaces to the left and right of "at"

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
     * @param input The entire user input.
     */
    public Command handleCommands(String input) throws DukeException {
        // isolates the command word
        input = input.trim();
        String[] words = input.split(" ");
        String command = words[0];

        switch (command) {
        case "bye": // only applicable to GUI Duke
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(parseIndex(words));
        case "deadline":
            return new DeadlineCommand(parseAddDeadline(input));
        case "todo":
            return new TodoCommand(parseTodo(input));
        case "event":
            return new EventCommand(parseAddEvent(input));
        case "delete":
            return new DeleteCommand(parseIndex(words));
        case "find":
            return new FindCommand(words);
        case "": // empty user input
            throw new EmptyCommandException();
        default: // all other inputs that are not supported
            throw new InvalidCommandException();
        }
    }
}
