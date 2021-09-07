package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.utils.Storage;

public class DeleteCommand extends Command {

    private static final String DELETE_MSG = "The following have been deleted:";

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task task = tasks.delete(this.index);

        String message = DELETE_MSG + "\n" + task.toString() + "\n" + tasks.getTaskCountString();

        return message;
    }
}
