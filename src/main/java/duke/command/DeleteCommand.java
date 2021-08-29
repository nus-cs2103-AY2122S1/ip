package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.Utils;
import duke.task.Task;

public class DeleteCommand extends Command {

    public DeleteCommand(String userCommand, String userArgument) {
        super(userCommand, userArgument);
    }

    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        int delete = Utils.getInputNumber(userArgument);

        if (delete >= tasks.numberOfTasks() || delete < 0) {
            throw new DukeException("Task does not exist!");
        }

        Task removedTask = tasks.getTask(delete);
        storage.deleteTaskFromFile(delete, tasks);
        tasks.removeTask(delete);

        ui.showMessage(String.format("I've removed this task:\n%s", removedTask.toString()));
        ui.showMessage(String.format("Now you have %d tasks in your list.\n", tasks.numberOfTasks()));
    }

    public boolean isExit() {
        return false;
    }
}
