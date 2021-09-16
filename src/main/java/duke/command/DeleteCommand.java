package duke.command;

import duke.exception.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Utils;
import duke.exception.TaskNotFoundException;
import duke.task.Task;

/** Command for deleting a task */
public class DeleteCommand extends Command {

    /**
     * Constructor for a DeleteCommand
     * @param userCommand The command the user gives
     * @param userArgument The argument (rest of the String after the command)
     */
    public DeleteCommand(String userCommand, String userArgument) {
        super(userCommand, userArgument);
    }

    /**
     * Deletes the task specified by the user
     * @return A string of the deleted task
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {

        assert userArgument != null;

        int indexToDelete = Utils.getInputNumber(userArgument);

        if (indexToDelete >= tasks.numberOfTasks() || indexToDelete < 0) {
            throw new TaskNotFoundException();
        }

        Task removedTask = tasks.getTask(indexToDelete);
        storage.deleteTaskFromFile(indexToDelete, tasks);
        tasks.removeTask(indexToDelete);

        String removedTaskString = String.format("I've removed this task:\n%s\n", removedTask.toString());
        String tasksLeftString = String.format("Now you have %d tasks in your list.\n", tasks.numberOfTasks());
        return removedTaskString + tasksLeftString;
    }
}
