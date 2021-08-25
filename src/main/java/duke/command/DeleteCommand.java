package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidArgumentException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class DeleteCommand extends Command {
    private int deleteTask;

    public DeleteCommand(int deleteTask) {
        this.deleteTask = deleteTask;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Check for invalid task argument
        if (deleteTask >= tasks.getSize()) {
            throw new InvalidArgumentException(tasks.getSize());
        }

        String response = "Noted. I've removed this task:\n"
                + "       " + tasks.getTask(deleteTask) + "\n"
                + "     Now you have "
                + (tasks.getSize() - 1) + (tasks.getSize() - 1 > 1 ? " tasks" : " task")
                + " in the list.";
        ui.showResponse(response);

        tasks.deleteTask(deleteTask);

        storage.save(tasks.getTaskList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
