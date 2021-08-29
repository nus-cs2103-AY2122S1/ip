package duke.command;

import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Represents the input of the user.
 *
 */
public abstract class Command {
//    BYE,
//    LIST,
//    DONE,
//    FIND,
//    DELETE,
//    DEADLINE,
//    EVENT,
//    TODO;

    private static final String INCOHERENT_INPUT_MESSAGE = "I'm sorry, but I don't know what that means :-(";

    /**
     * Creates a command, throwing an error if it's not part of the enums
     *
     * @param commandString The first word in the input
     * @return A command representing one of the enums above
     * @throws DukeException An exception thrown according to the message given
     */
    public static Command initialiseCommand(String commandString, String remainingText) throws DukeException {
            switch (commandString) {
            case TodoCommand.COMMAND:
                return new TodoCommand(remainingText);
            case ListCommand.COMMAND:
                return new ListCommand(remainingText);
            case FindCommand.COMMAND:
                return new FindCommand(remainingText);
            case EventCommand.COMMAND:
                return new EventCommand(remainingText);
            case DoneCommand.COMMAND:
                return new DoneCommand(remainingText);
            case DeleteCommand.COMMAND:
                return new DeleteCommand(remainingText);
            case DeadlineCommand.COMMAND:
                return new DeadlineCommand(remainingText);
            case ByeCommand.COMMAND:
                return new ByeCommand();
            default:
                throw new DukeException(INCOHERENT_INPUT_MESSAGE);
            }
    }

    /**
     * Executes the command.
     *
     * @param taskList The taskList keeping track of the tasks
     * @param ui The Ui used for the user interface
     * @param storage The storage object taking care of writing and reading the text file
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns a boolean on whether to exit the inputLoop or not.
     *
     * @return true if the inputLoop should be exited
     */
    public abstract boolean isExit();
}
