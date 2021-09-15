package duke.command;

import duke.exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Utils;
import duke.exception.TaskNotFoundException;
import duke.task.Task;

public class DeleteCommand extends Command {

    public DeleteCommand(String userCommand, String userArgument) {
        super(userCommand, userArgument);
    }

    public String execute(TaskList tasks, Storage storage) throws DukeException {

        assert userArgument != null;

        int indexToDelete = Utils.getInputNumber(userArgument);

        if (indexToDelete >= tasks.numberOfTasks() || indexToDelete < 0) {
            throw new TaskNotFoundException();
        }

        Task removedTask = tasks.getTask(indexToDelete);
        storage.deleteTaskFromFile(indexToDelete, tasks);
        tasks.removeTask(indexToDelete);

        String removedTaskString = String.format("I've removed this task:\n%s", removedTask.toString());
        String tasksLeftString = String.format("Now you have %d tasks in your list.\n", tasks.numberOfTasks());
        return removedTaskString + tasksLeftString;
    }
}
