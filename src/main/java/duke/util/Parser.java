package duke.util;

import duke.exception.DukeException;
import duke.exception.NoCommandException;
import duke.exception.NoDescriptionException;
import duke.exception.NoTimeException;

import java.text.ParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Encapsulates the parsing of user inputs.
 */
public class Parser {

    /**
     * Returns string response for each user input.
     *
     * @param fullCommand Command entered by user.
     * @return String representation of duke's response.
     * @throws DukeException If user input does not meet requirement.
     */
    public static Command parseCommand(String fullCommand) throws DukeException {

        String[] command = fullCommand.split(" ", 2);
        String commandWord = command[0].trim();

        switch (commandWord) {
        case "todo":
            return new TodoCommand(command);
        case "event":
            return new EventCommand(command);
        case "deadline":
            return new DeadlineCommand(command);
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "delete":
            return new DeleteCommand(command);
        case "done":
            return new DoneCommand(command);
        case "find":
            return new FindCommand(command);
        case "archive":
            return new ArchiveCommand();
        default:
            throw new NoCommandException();
        }
    }

    /**
     * Returns date formatted as LocalDate.
     *
     * @param dateTime Date entered by user.
     * @return A date of the LocalDate format.
     * @throws ParseException If dateTime is not of required format.
     */
    public static LocalDate parseDate(String dateTime) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate date = LocalDate.parse(dateTime, formatter);
        return date;
    }

    /**
     * Returns array of string that stores description and date of task.
     *
     * @param userInput Array that stores task type and details of task.
     * @param regex Regex to split the details of task into description and date.
     * @param taskType Type of task.
     * @return Array of string containing description and date respectively.
     * @throws DukeException If userInput doesn't contain description or date.
     */
    public static String[] parseDescriptionAndTime(String[] userInput,String regex,
                                                    String taskType) throws DukeException {

        String[] next = userInput[1].trim().split(regex);

        if (next[0].trim().length() == 0) {
            throw new NoDescriptionException(taskType);
        } else if (next.length < 2) {
            throw new NoTimeException(taskType);
        }

        assert next.length == 2;

        return next;
    }

}
