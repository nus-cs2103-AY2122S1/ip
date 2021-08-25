package duke.command;

import duke.data.Storage;
import duke.data.TaskList;
import duke.exception.InvalidTaskNoException;

/**
 * Represents a processor that can delete a task from the task list. A subclass of the Processor class.
 */
public class DeleteTaskCommand extends Command {
    int taskIndex;
    /**
     * Constructor of the class `DeleteATaskProcessor`.
     */
    public DeleteTaskCommand(int taskIndex) {
        super("delete");
        this.taskIndex = taskIndex;
    }

    /**
     * Updates the message to be printed.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws InvalidTaskNoException {
        try {
            this.task = tasks.get(this.taskIndex);
        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTaskNoException();
        }
        tasks.removeFromList(this.task);
        storage.removeFromFile(tasks.indexOf(this.task));
        this.message = String.format(
                "Noted. I've removed this task:\n  %s\nNow you have %o tasks in the list.\n",
                this.task, tasks.getNumOfTasks());
    }
}
