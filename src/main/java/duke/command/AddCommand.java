package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a Command that adds a Task to the TaskList.
 * An AddCommand consists of a Task.
 *
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Creates an AddCommand Object.
     *
     * @param task Task to be added to the TaskList.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds task to TaskList.
     *
     * @param tasks TaskList to add task to.
     * @param ui UI to print out the output after task has been added.
     * @param storage unused.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        return ui.printAdd(task, tasks);
    }

    /**
     * Returns Task attached to this AddCommand
     *
     * @return Task attached to this AddCommand
     */
    public Task getTask() {
        return task;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AddCommand)) {
            return false;
        } else {
            AddCommand oAddCommand = (AddCommand) o;
            return task.toString().equals((oAddCommand.getTask().toString()));
        }
    }
}