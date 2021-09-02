package duke.command;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.task.Task;
import duke.storage.Storage;

/**
 * This class abstracts the delete command that the user wants to execute.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private final int taskNum;

    /**
     * Constructs a DeleteCommand that will delete the given task number when executed.
     *
     * @param taskNum The index of the task to be executed.
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Execute the command to delete the task from the given TaskList.
     *
     * @param tasks   The TaskList of the Duke instance.
     * @param storage The storage handler of the Duke instance.
     * @throws DukeException The checked exception to be thrown when execution fails.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (0 <= taskNum && taskNum < tasks.size()) {
            Task removedTask = tasks.remove(taskNum);
            storage.update(tasks);
            return "Got it. I've removed this task:\n  "
                    + removedTask
                    + "\nNow you have " + tasks.size() + " tasks in the list.";
        } else {
            throw new DukeException("OOPS!!! Please enter a valid task number");
        }
    }
}
