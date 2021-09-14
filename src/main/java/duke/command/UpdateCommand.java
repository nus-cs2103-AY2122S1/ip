package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents the command of updating a task.
 */
public class UpdateCommand extends Command {

    private final int taskNum;

    private final Task updatedTask;

    /**
     * Creates the command that will update the task in the taskList.
     *
     * @param taskNum The taskNum of the task that will be updated.
     * @param updatedTask The task that contains the updated fields.
     */
    public UpdateCommand(int taskNum, Task updatedTask) {
        this.taskNum = taskNum;
        this.updatedTask = updatedTask;
    }

    private void updateTask(Task task, Task updatedTask) throws DukeException {
        if (task instanceof Todo) {
            Todo todo = (Todo) task;
            todo.updateTodo((Todo) updatedTask);
        } else if (task instanceof Event) {
            Event event = (Event) task;
            event.updateEvent((Event) updatedTask);
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            deadline.updateDeadline((Deadline) updatedTask);
        } else {
            throw new DukeException("Wrong format for updating of task!");
        }
    }

    /**
     * Executes the command by updating the task.
     *
     * @param taskList The taskList where the task will be added.
     * @param ui The ui where the message will be printed.
     * @param storage The storage where it will be updated with the new task.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        updateTask(taskList.getAllTasks().get(taskNum - 1), updatedTask);
        return String.format("Task %d successfully updated!", taskNum);
    }
}
