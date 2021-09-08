package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.exceptions.DukeException;

/**
 * Parser class parses the user inputs.
 */
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
    public static final String COMMAND_FIND = "find";
    public static final String COMMAND_HELP = "help";

    private static final String DELIMITER = " ";

    /**
     * Error Messages.
     */
    private static final String ERROR_UNKNOWN_COMMAND = "Unknown Command. Type 'help' for more information!";

    /**
     * Returns the Command corresponding to the full String duke.command input.
     * String input examples:
     * bye
     * list
     * todo read book
     * deadline return book /by 2021-10-15
     * deadline return book /by 2021-10-15 18:00
     * event book fest /at 2021-10-15
     * event book fest /at 2021-10-15 18:00
     * done 2
     *
     * @param fullCommand Full String duke.command input by user.
     * @return Corresponding duke.command from the input.
     */
    public static Command parse(String fullCommand) throws DukeException {
        // Split the fullCommand String to get the first duke.command in the String.
        String[] commands = fullCommand.split(DELIMITER, 2);
        String firstCommand = commands[0];
        Command command = null;

        switch (firstCommand) {
        case COMMAND_EXIT:
            command = new ExitCommand();
            break;
        case COMMAND_HELP:
            command = new HelpCommand();
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
        case COMMAND_FIND:
            command = new FindCommand(fullCommand, DELIMITER);
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
