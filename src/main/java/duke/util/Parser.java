package duke.util;

import duke.exception.DukeException;
import duke.exception.NoCommandException;
import duke.exception.NoDescriptionAndTimeException;
import duke.exception.NoDescriptionException;
import duke.exception.NoTimeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


/**
 * Encapsulates the parsing of user inputs.
 */
public class Parser {

    /**
     * Returns string response for each user input.
     *
     * @param fullCommand Command entered by user.
     * @return String representation of duke's response.
     * @throws DukeException If user input does not match any command words.
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
     * Returns array of string that stores description and date of task.
     *
     * @param userInput Array that stores task type and details of task.
     * @param regex Regex to split the task details into description and date.
     * @param taskType Type of task.
     * @return Array of string containing description and date respectively.
     * @throws DukeException If userInput doesn't contain description or date.
     */
    public static String[] parseDescriptionAndTime(String[] userInput,String regex,
                                                    String taskType) throws DukeException {

        String[] descriptionTime = userInput[1].trim().split(regex);

        if(descriptionTime.length == 0) {
            throw new NoDescriptionAndTimeException(taskType);
        }

        if (descriptionTime[0].trim().length() == 0) {
            throw new NoDescriptionException(taskType);
        } else if (descriptionTime.length < 2) {
            throw new NoTimeException(taskType);
        }

        assert descriptionTime.length == 2;

        return descriptionTime;
    }

    /**
     * Returns date and time formatted as LocalDateTime.
     *
     * @param userInput Date and time entered by user.
     * @return Date and time of the LocalDateTime format.
     * @throws DukeException If dateTime is not of required format.
     */
    public static LocalDateTime parseDateTime(String userInput) throws DukeException {
        String[] dateTime = userInput.split(",", 2);

        if (dateTime.length < 2) {
            throw new DukeException("Please enter date and time of format yyyy-MM-dd, HH:mm!");
        }

        LocalDate localDate = LocalDate.parse(dateTime[0].trim());
        LocalTime localTime = LocalTime.parse(dateTime[1].trim());
        return LocalDateTime.of(localDate, localTime);
    }

}
