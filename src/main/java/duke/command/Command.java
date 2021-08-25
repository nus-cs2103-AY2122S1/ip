package duke.command;

import duke.DukeException;
import duke.FileManager;
import duke.Tasklist;
import duke.Ui;
import duke.task.Task;

public abstract class Command {
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

    public abstract void execute(Tasklist task, Ui ui, FileManager fileManager) throws DukeException;
    public abstract boolean isExit();
}
