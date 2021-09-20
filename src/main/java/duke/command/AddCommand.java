package duke.command;

import duke.Archive;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.errors.FileException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Type of Command that adds tasks to the task list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor.
     *
     * @param task
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes operations depending on the type of task.
     *  @param taskList current list of tasks.
     * @param ui
     * @param storage
     * @param archive
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage, Archive archive) throws FileException {
        taskList.addTask(this.task);

        if (task instanceof Todo) {
            Todo todo = (Todo) this.task;
            storage.add(todo);
            return ui.add(taskList, todo);
        }

        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) this.task;
            storage.add(deadline);
            return ui.add(taskList, deadline);
        }

        if (task instanceof Event) {
            Event event = (Event) this.task;
            storage.add(event);
            return ui.add(taskList, event);
        }
        return ui.showError("unable to add Task to Duke");
    }

    /**
     * Returns false, because it is not a Exit Command.
     *
     * @return false;
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
