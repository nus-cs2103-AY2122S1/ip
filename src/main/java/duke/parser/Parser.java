package duke.parser;

import java.time.LocalDate;
import java.util.Arrays;

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

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Converts the user input of an task date into a LocalDate object.
     *
     * @param eventDate The string representing the date inputted by the user.
     * @return The LocalDate object representing the task date.
     * @throws DukeException if user input is of an incorrect format.
     */
    public static LocalDate convertDate(String eventDate) throws DukeException {
        if (eventDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return LocalDate.parse(eventDate);
        } else {
            throw new DukeException("Please follow date format: yyyy-mm-dd");
        }
    }

    /**
     * Parses user input and executes the appropriate response.
     *
     * @param fullCommand The string representing the command inputted by the user.
     * @return The command based on the user input.
     * @throws DukeException If user input is invalid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String command;
        String arguments;

        if (fullCommand.isEmpty()) {
            throw new DukeException("Please enter a command");
        }

        if (!fullCommand.contains(" ")) {
            command = fullCommand;
            arguments = "";
        } else {
            command = fullCommand.substring(0, fullCommand.indexOf(" "));
            arguments = fullCommand.substring(fullCommand.indexOf(" ") + 1).trim();
        }

        switch (command) {
        case "list":
        case "li":
            return new ListCommand();
        case "bye":
            return new ByeCommand();
        case "delete":
        case "del":
            return new DeleteCommand(arguments);
        case "done":
            return new DoneCommand(arguments);
        case "event":
        case "e":
            return new EventCommand(arguments);
        case "deadline":
        case "d":
            return new DeadlineCommand(arguments);
        case "todo":
        case "t":
            return new TodoCommand(arguments);
        case "find":
        case "f":
            return new FindCommand(arguments);
        default:
            throw new DukeException("I'm sorry, but I don't know what \""
                    + fullCommand + "\" means :-(");
        }
    }

    /**
     * Checks if task argument is empty during add commands.
     *
     * @param command   command used.
     * @param arguments arguments passed along with the command.
     * @throws DukeException if no arguments are given.
     */
    public static void checkAddTaskArgument(String command, String arguments) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException(String.format("The description of a %s cannot be left empty. "
                    + "Please try again.", command));
        }
    }

    /**
     * Parses the argument depending on the task.
     *
     * @param command   command used.
     * @param arguments arguments passed along with the command.
     * @return The string array containing arguments split by description and time.
     * @throws DukeException if arguments don't follow correct format.
     */
    public static String[] parseArguments(String command, String arguments) throws DukeException {
        String[] argArr;
        switch (command) {
        case "deadline":
            argArr = arguments.split("/by");
            if (argArr.length == 1 || argArr[1].isEmpty()) {
                throw new DukeException("Arguments do not follow proper format. Don't forget the /by");
            }
            break;
        case "event":
            argArr = arguments.split("/at");
            if (argArr.length == 1 || argArr[1].isEmpty()) {
                throw new DukeException("Arguments do not follow proper format. Don't forget the /at");
            }
            break;
        default:
            throw new DukeException("Invalid task type!");
        }
        return Arrays.stream(argArr).map(String::trim).toArray(String[]::new);
    }

    /**
     * Parses the index for done and delete commands.
     *
     * @param arguments The arguments passed along with command.
     * @param taskSize  The length of the task list.
     * @return The index given as an int.
     * @throws DukeException if no index or invalid index given.
     */
    public static int parseIndex(String arguments, int taskSize) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException("No index was keyed in. Please try again.");
        }

        int index = Integer.parseInt(arguments);
        if (index < 1 || index > taskSize) {
            throw new DukeException("The index you entered is invalid. Please try again.");
        }
        return index;
    }

}
