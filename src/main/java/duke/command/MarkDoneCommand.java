package duke.command;

import duke.FileController;
import duke.exceptions.InvalidOperationDukeException;
import duke.exceptions.UnsavedChangesException;
import duke.tasks.TaskList;

public class MarkDoneCommand extends Command {

    private int index;

    public MarkDoneCommand(int index) {
        this.index = index;
    }

    public String execute(TaskList tasks, FileController fc) throws InvalidOperationDukeException, UnsavedChangesException {
        if (index < 1 || index > tasks.size()) {
            throw new InvalidOperationDukeException("Number is out of bounds");
        }
        tasks.markDoneAndAddNextTask(index - 1);
        assert tasks.get(index - 1).isDone();

        if (!fc.writeText(tasks.serialize())) {
            throw new UnsavedChangesException();
        }
        return String.format("Task %d is done\n%s", index, tasks.get(index - 1));
    }
}
