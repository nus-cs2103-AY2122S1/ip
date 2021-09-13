package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskHandler;
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
     * @return Message to be shown to user.
     * @throws DukeException If there is error adding the task or saving data.
     */
    @Override
    public String execute(TaskHandler taskHandler, Storage storage, Ui ui) throws DukeException {
        assert task != null : "Empty task.";
        storage.writeToFile(task);
        return taskHandler.addTask(task);
    }

    /**
     * {@inheritDoc}
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
