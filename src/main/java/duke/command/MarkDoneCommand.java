package duke.command;

import duke.FileController;
import duke.UI;
import duke.exceptions.InvalidOperationDukeException;
import duke.exceptions.UnsavedChangesException;
import duke.tasks.TaskList;

public class MarkDoneCommand extends Command {

    private int index;

    public MarkDoneCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, UI ui, FileController fc) throws InvalidOperationDukeException, UnsavedChangesException {
        if (index < 1 || index > tasks.size()) {
            throw new InvalidOperationDukeException("Number is out of bounds");
        }
        tasks.get(index - 1).markDone();
        if (!fc.writeText(tasks.serialize())) {
            throw new UnsavedChangesException();
        }
        ui.printText(String.format("Task %d is done\n%s", index, tasks.get(index - 1)));
    }
}
