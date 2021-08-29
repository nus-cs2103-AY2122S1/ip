package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
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
     *
     * @param taskList current list of tasks.
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(this.task);

        if (task instanceof Todo) {
            Todo todo = (Todo) this.task;
            storage.addTodo(todo);
            ui.addTodo(taskList, todo);
        }

        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) this.task;
            storage.addDeadline(deadline);
            ui.addDeadline(taskList, deadline);
        }

        if (task instanceof Event) {
            Event event = (Event) this.task;
            storage.addEvent(event);
            ui.addEvent(taskList, event);
        }
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
