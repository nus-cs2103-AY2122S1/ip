package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Utils;
import duke.task.Task;

public class DeleteCommand extends Command {

    public DeleteCommand(String userCommand, String userArgument) {
        super(userCommand, userArgument);
    }

    public String execute(TaskList tasks, Storage storage) throws DukeException {
        int delete = Utils.getInputNumber(userArgument);

        if (delete >= tasks.numberOfTasks() || delete < 0) {
            throw new DukeException("Task does not exist!");
        }

        Task removedTask = tasks.getTask(delete);
        storage.deleteTaskFromFile(delete, tasks);
        tasks.removeTask(delete);

        String removedTaskString = String.format("I've removed this task:\n%s", removedTask.toString());
        String tasksLeftString = String.format("Now you have %d tasks in your list.\n", tasks.numberOfTasks());
        return removedTaskString + tasksLeftString;
    }
}
