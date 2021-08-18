package duke.command;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

public abstract class Command {
    protected boolean isDone = false;

    public abstract void execute(TaskList tasklist, Ui ui, Storage store);

    public boolean isExit() {
        return isDone;
    }
}
