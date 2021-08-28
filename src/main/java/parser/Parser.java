package parser;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.DoneCommand;
import command.ExitCommand;
import command.ListCommand;

import exceptions.DukeException;

public class Parser {
    /**
     * List of Command Strings.
     */
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_DELETE = "delete";

    private static final String DELIMITER = " ";

    /**
     * Error Messages.
     */
    private static final String ERROR_UNKNOWN_COMMAND = "Unknown Command. See README for full list of commands.";

    /**
     * Returns the Command corresponding to the full String command input.
     * String input examples:
     * <p>
     * bye
     * list
     * todo read book
     * deadline return book /by 2021-10-15
     * deadline return book /by 2021-10-15 18:00
     * event book fest /at 2021-10-15
     * event book fest /at 2021-10-15 18:00
     * done 2
     *
     * @param fullCommand Full String command input by user.
     * @return Corresponding command from the input.
     */
    public static Command parse(String fullCommand) throws DukeException {
        // Split the fullCommand String to get the first command in the String.
        String[] commands = fullCommand.split(DELIMITER, 2);
        String firstCommand = commands[0];
        Command command = null;

        switch (firstCommand) {
        case COMMAND_EXIT:
            command = new ExitCommand();
            break;
        case COMMAND_LIST:
            command = new ListCommand();
            break;
        case COMMAND_TODO:
            command = new AddCommand(fullCommand, DELIMITER, AddCommand.TaskType.TODO);
            break;
        case COMMAND_DEADLINE:
            command = new AddCommand(fullCommand, DELIMITER, AddCommand.TaskType.DEADLINE);
            break;
        case COMMAND_EVENT:
            command = new AddCommand(fullCommand, DELIMITER, AddCommand.TaskType.EVENT);
            break;
        case COMMAND_DONE:
            command = new DoneCommand(fullCommand, DELIMITER);
            break;
        case COMMAND_DELETE:
            command = new DeleteCommand(fullCommand, DELIMITER);
            break;
        default:
            break;
        }

        if (command == null) {
            throw new DukeException(ERROR_UNKNOWN_COMMAND);
        }

        return command;
    }
}
