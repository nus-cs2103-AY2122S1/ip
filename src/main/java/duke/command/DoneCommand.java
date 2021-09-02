package duke.command;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;

/**
 * This class abstracts the done command that the user wants to execute.
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    private final int taskNum;

    /**
     * Constructs a DoneCommand that will mark the task at the given task index once executed.
     *
     * @param taskNum The index of the task to be marked as done.
     */
    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Execute the command to mark the given task as done.
     *
     * @param tasks   The TaskList of the Duke instance.
     * @param storage The storage handler of the Duke instance.
     * @throws DukeException The checked exception to be thrown when execution fails.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (0 <= taskNum && taskNum < tasks.size()) {
            tasks.get(taskNum).markAsDone();
            storage.update(tasks);
            return "Nice! I've marked this task as done:\n  "
                    + tasks.get(taskNum).toString();
        } else {
            throw new DukeException("OOPS!!! Please enter a valid task number");
        }
    }
}
