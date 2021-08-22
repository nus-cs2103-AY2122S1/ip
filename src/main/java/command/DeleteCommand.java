package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import dukeException.DukeException;
import task.Task;

public class DeleteCommand extends Command{
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = taskList.delete(this.taskIndex);
        ui.printMessage(new String[] {
                "Noted. I've removed this task:",
                "  " + deletedTask.toString(),
                "Now you have " + taskList.amountOfTasks() + " tasks in the list."});
    }
}
