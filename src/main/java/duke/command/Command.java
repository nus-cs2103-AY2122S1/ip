package duke.command;

import duke.DukeException;
import duke.FileManager;
import duke.Tasklist;
import duke.UI;
import duke.task.Task;

/**
 * Class which represents a command to be run by Duke.
 */
public abstract class Command {
    /**
     * Creates a command.
     *
     * @param type the type of command.
     * @param task the task to be acted upon in the command.
     * @param index the index of the task to be acted upon in the tasklist.
     * @return command made based on input.
     * @throws DukeException if input is invalid.
     */
    public static Command makeCommand(CommandsTypes type, Task task, int index) throws DukeException {
        switch (type) {
        case Add: {
            return new AddCommand(task);
        }
        case Delete: {
            return new DeleteCommand(task, index);
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
     * Command will execute.
     *
     * @param task the task to be executed in the command.
     * @param ui the ui to interact with the user.
     * @param fileManager the filemanger that manages the storage of duke.
     * @throws DukeException if command has issues.
     */
    public abstract void execute(Tasklist task, UI ui, FileManager fileManager) throws DukeException;
    public abstract boolean isExit();
}
