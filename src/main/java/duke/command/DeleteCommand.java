package duke.command;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;

public class DeleteCommand extends Command{
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage store) throws IndexOutOfBoundsException {
        Task removed = tasklist.delete(index);
        ui.notifySuccessfulDelete(tasklist, removed);
    }
}
