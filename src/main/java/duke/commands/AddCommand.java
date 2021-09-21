package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.utils.Storage;

public class AddCommand extends Command {

    private static final String NEW_TASK_MSG = "New task added:";

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.add(this.task);

        String message = NEW_TASK_MSG + "\n" + this.task.toString() + "\n" + tasks.getTaskCountString();

        return message;
    }
}
