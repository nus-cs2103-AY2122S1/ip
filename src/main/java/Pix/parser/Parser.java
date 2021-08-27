package Pix.parser;

import Pix.command.*;
import Pix.exception.*;
import Pix.task.Deadline;
import Pix.task.Event;
import Pix.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    /**
     * Parser that parses the user input.
     *
     * @param command The command that the user inputted in the Ui.
     *
     * @return Returns the command corresponding to the user input.
     *
     * @throws PixException Throws PixException if the user puts in invalid input.
     */
    public static Command parse(String command) throws PixException {
        String[] splitCommand = command.split(" ", 2);
        switch (splitCommand[0]) {
        case "bye":
            return getExitCommand(splitCommand);
        case "todo":
            return getTodoCommand(splitCommand);
        case "deadline":
            return getDeadlineCommand(splitCommand);
        case "event":
            return getEventCommand(splitCommand);
        case "list":
            return getListCommand(splitCommand);
        case "done":
            return getDoneCommand(splitCommand);
        case "delete":
            return getDeleteCommand(splitCommand);
        case "find":
            return getFindCommand(splitCommand);
        default:
            throw new PixUnknownCommandException();
        }
    }

    /**
     * Runs the exit command.
     *
     * @param splitCommand Command input from the user.
     *
     * @return Returns the command for exiting Pix.Pix.
     *
     * @throws PixInvalidTaskException Throws this Pix.exception if the format is incorrect.
     */
    private static Command getExitCommand(String[] splitCommand) throws PixInvalidTaskException {
        if (splitCommand.length > 1) {
            throw new PixInvalidTaskException();
        }

        return new ExitCommand();
    }

    /**
     * Runs the add command with a todo Pix.task.
     *
     * @param splitCommand String input of the command.
     *
     * @return Returns the add command with a todo Pix.task.
     *
     * @throws PixMissingInfoException Throws the exception when not enough information is provided.
     */
    private static Command getTodoCommand(String[] splitCommand) throws PixMissingInfoException {
        if (splitCommand[1].isBlank()) {
            throw new PixMissingInfoException(splitCommand[0]);
        }

        return new AddCommand(new Todo(splitCommand[1]));
    }

    /**
     * Runs the add command with a deadline Pix.task.
     *
     * @param splitCommand String input of the command.
     *
     * @return Returns the add command with a deadline task.
     *
     * @throws PixMissingInfoException Throws the exception when not enough information is provided.
     */
    private static Command getDeadlineCommand(String[] splitCommand)
            throws PixMissingInfoException, PixInvalidDateException {
        try {
            String[] taskDetails = splitCommand[1].split(" /by ", 2);
            if (taskDetails[0].isBlank() || taskDetails[1].isBlank()) {
                throw new PixMissingInfoException(splitCommand[0]);
            }
            return new AddCommand(new Deadline(taskDetails[0], LocalDate.parse(taskDetails[1])));
        } catch (DateTimeParseException e) {
            throw new PixInvalidDateException();
        }
    }

    /**
     * Runs the add command with an event task.
     *
     * @param splitCommand String input of the command.
     *
     * @return Returns the add command with an event task.
     *
     * @throws PixMissingInfoException Throws the exception when not enough information is provided.
     */
    private static Command getEventCommand(String[] splitCommand)
            throws PixMissingInfoException, PixInvalidDateException {
        try {
            String[] taskDetails = splitCommand[1].split(" /at ", 2);
            if (taskDetails[0].isBlank() || taskDetails[1].isBlank()) {
                throw new PixMissingInfoException(splitCommand[0]);
            }
            return new AddCommand(new Event(taskDetails[0], LocalDate.parse(taskDetails[1])));
        } catch (DateTimeParseException e) {
            throw new PixInvalidDateException();
        }
    }

    /**
     * Runs the list command.
     *
     * @return Returns the list command.
     *
     * @throws PixMissingInfoException Throws the exception when not enough information is provided.
     */
    private static Command getListCommand(String[] splitCommand) throws PixMissingInfoException {
        if (splitCommand.length > 1) {
            throw new PixMissingInfoException(splitCommand[0]);
        }

        return new ListCommand();
    }

    /**
     * Runs the done command.
     *
     * @return Returns the done command.
     *
     * @throws PixMissingInfoException Throws the exception when not enough information is provided.
     */
    private static Command getDoneCommand(String[] splitCommand)
            throws PixMissingInfoException, PixNumberFormatException {
        try {
            if (splitCommand[1].isBlank()) {
                throw new PixMissingInfoException(splitCommand[0]);
            }
            return new DoneCommand(Integer.parseInt(splitCommand[1]));
        } catch (NumberFormatException e) {
            throw new PixNumberFormatException();
        }
    }

    /**
     * Runs the delete command.
     *
     * @return Returns the delete command.
     *
     * @throws PixMissingInfoException Throws the exception when not enough information is provided.
     */
    private static Command getDeleteCommand(String[] splitCommand)
            throws PixMissingInfoException, PixNumberFormatException {
        try {
            return new DeleteCommand(Integer.parseInt(splitCommand[1]));
        } catch (NumberFormatException e) {
            throw new PixNumberFormatException();
        } catch (IndexOutOfBoundsException e) {
            throw new PixMissingInfoException(splitCommand[0]);
        }
    }

    /**
     * Runs the find command.
     *
     * @return Returns the find command.
     *
     * @throws PixMissingInfoException Throws the exception when not enough information is provided.
     */
    private static Command getFindCommand(String[] splitCommand) throws PixMissingInfoException {
        String keyword = splitCommand[1];
        String[] finalKeyword = keyword.split(" ", 0);
        if (finalKeyword.length > 1 || keyword.isBlank()) {
            throw new PixMissingInfoException(splitCommand[0]);
        }

        return new FindCommand(keyword);
    }
}