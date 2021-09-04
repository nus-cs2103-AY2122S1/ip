package duke.command;

import duke.FileController;
import duke.exceptions.InvalidOperationDukeException;
import duke.exceptions.UnsavedChangesException;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class DeleteTaskCommand extends Command {

    private int index;

    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    public String execute(TaskList tasks, FileController fc) throws UnsavedChangesException {
        if (index < 1 || index > tasks.size()) {
            throw new InvalidOperationDukeException("Number is out of bounds");
        }
        Task task = tasks.remove(index - 1);

        if (!fc.writeText(tasks.serialize())) {
            throw new UnsavedChangesException();
        }
        return String.format("Removed task %d %s", index, task);
    }
}
