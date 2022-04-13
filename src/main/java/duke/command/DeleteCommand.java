package duke.command;

import duke.DukeException;
import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a Command that deletes a Task from the TaskList.
 */
public class DeleteCommand extends Command {
    /**
     * The task number to be deleted.
     */
    private final Integer taskNum;

    /**
     * Constructs a delete command with a task number.
     *
     * @param taskNum The task number to be deleted.
     */
    public DeleteCommand(Integer taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the delete task command.
     *
     * @param tasks   The task list to execute the command on.
     * @param storage The storage for the tasks.
     * @return a string output.
     * @throws DukeException
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task task = tasks.deleteTask(taskNum);

        storage.save(tasks.getListData());

        return formatOutput("Ok, I've deleted this task:", task.toString(),
            "Now you have " + tasks.getListSize() + " tasks in the list.");
    }

    /**
     * Returns false to continue the program.
     *
     * @return false.
     */
    public boolean isExit() {
        return false;
    }
}
