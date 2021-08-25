package duke.command;

import duke.data.Storage;
import duke.data.TaskList;
import duke.exception.InvalidTaskNoException;

/**
 * Represents a processor that can mark a task as done. A subclass of the Processor class.
 */
public class TaskDoneCommand extends Command {
    int taskIndex;
    /**
     * Constructor of the class `TaskDoneProcessor`.
     */
    public TaskDoneCommand(int taskIndex) {
        super("done");
        this.taskIndex = taskIndex;
        this.message = "Nice! I've marked this task as done:\n";
    }

    /**
     * Generates the message to be printed.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) throws InvalidTaskNoException {
        try {
            this.task = taskList.get(this.taskIndex);
        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTaskNoException();
        }
        this.task.setDone();
        storage.rewriteFile();
        this.message += String.format("  %s\n", this.task.toString());
    }
}
