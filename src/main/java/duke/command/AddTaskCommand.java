package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.util.TaskHandler;
import duke.util.Storage;
import duke.util.Ui;

/**
 * This class encapsulates commands that add tasks to the list.
 *
 * @author Teo Sin Yee
 */
public class AddTaskCommand extends Command {
    private final Task task;

    /**
     * Instantiates a new AddTaskCommand
     *
     * @param task Task to be added to task list.
     */
    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task to the task list.
     * Writes task data to storage file.
     *
     * @param taskHandler TaskHandler of Duke.
     * @param storage Storage for Duke.
     * @param ui User interface.
     * @throws DukeException If there is error adding the task or saving data.
     */
    @Override
    public void execute(TaskHandler taskHandler, Storage storage, Ui ui) throws DukeException {
        taskHandler.addTask(task);
        storage.writeToFile(task);
    }

    /**
     * Indicates whether some other object is equal to this one.
     *
     * @param o The reference object with which to compare.
     * @return True if this object is the same as the obj argument, False otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof AddTaskCommand) {
            return ((AddTaskCommand) o).task.equals(this.task);
        }
        return false;
    }
}