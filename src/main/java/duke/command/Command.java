package duke.command;
import duke.task.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

public abstract class Command {
    public abstract void execute(TaskList ls, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}
