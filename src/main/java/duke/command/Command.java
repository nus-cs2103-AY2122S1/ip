package duke.command;

import duke.DukeException;
import duke.FileManager;
import duke.Tasklist;
import duke.Ui;
import duke.task.Task;

/**
 * Class which represents a command to be run by Duke.
 */
public abstract class Command {
    /**
     * Makes a command based on input.
     * @param type command type.
     * @param task the task to be executed.
     * @return the command that is made.
     * @throws DukeException if input is invalid.
     */
    public static Command makeCommand(CommandsTypes type, Task task) throws DukeException {
        switch (type) {
        case Add: {
            return new AddCommand(task);
        }
        default:
            throw new DukeException("Invalid command inputted");
        }
    }

    /**
     * Makes a command based on input.
     * @param type command type.
     * @param index index of command to be executed.
     * @return the command that is made.
     * @throws DukeException if input is invalid.
     */
    public static Command makeCommand(CommandsTypes type, int index) throws DukeException {
        switch (type) {
        case Delete: {
            return new DeleteCommand(index);
        }
        case MarkDone: {
            return new MarkDoneCommand(index);
        }
        case Exit: {
            return new ExitCommand();
        }
        case List: {
            return new ListCommand();
        }
        default:
            throw new DukeException("Invalid command inputted");
        }
    }

    /**
     * Makes a command based on input.
     * @param type command type.
     * @return the command that is made.
     * @throws DukeException if input is invalid.
     */
    public static Command makeCommand(CommandsTypes type) throws DukeException {
        switch (type) {
        case Exit: {
            return new ExitCommand();
        }
        case List: {
            return new ListCommand();
        }
        default:
            throw new DukeException("Invalid command inputted");
        }
    }

    /**
     * Makes a command based on input.
     * @param type command type.
     * @param keywords keywords to be executed on.
     * @return the command that is made.
     * @throws DukeException if input is invalid.
     */
    public static Command makeCommand(CommandsTypes type, String ...keywords) throws DukeException {
        switch (type) {
        case Find:
            return new FindCommand(keywords);
        default:
            throw new DukeException("Invalid command inputted");
        }
    }

    /**
     * Command will execute.
     *
     * @param task the task to be executed in the command.
     * @param ui the ui to interact with the user.
     * @param fileManager the filemanger that manages the storage of duke.
     * @throws DukeException if command has issues.
     */
    public abstract String execute(Tasklist task, Ui ui, FileManager fileManager) throws DukeException;
    public abstract boolean isExit();
}
