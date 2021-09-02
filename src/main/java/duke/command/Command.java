package duke.command;
import duke.Ui;
import duke.Storage;
import duke.DukeException;
import duke.task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList ls, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}
