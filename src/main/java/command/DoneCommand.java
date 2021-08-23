package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import dukeException.DukeException;
import task.Task;

public class DoneCommand extends Command {
    public int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task doneTask = taskList.markAsDone(this.taskIndex);
        ui.printMessage(new String[] {
                "Nice! I've marked this task as done:",
                doneTask.toString()});
    }
}
