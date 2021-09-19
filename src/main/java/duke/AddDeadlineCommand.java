package duke;

import java.util.List;

/**
 * Represents a command to add deadline
 */
public class AddDeadlineCommand extends Command {
    private final Deadline deadline;

    /**
     * Constructs an instance of <code>AddDeadlineCommand</code>
     * @param deadline <code>Deadline</code> object
     */
    public AddDeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    /**
     * Executes <code>AddDeadlineCommand</code>
     * @param tasks <code>TaskList</code> containing saved tasks
     * @param ui <code>Ui</code> responsible for user interactions
     * @param storage <code>Storage</code> responsible for saving tasks to drive
     * @return corresponding message
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.deadline);
        return returnAddedMessage(this.deadline, tasks);
    }

    /**
     * Returns a task added message with the corresponding task
     * @param task <code>Task</code> added
     * @param taskList the current tasks
     * @return <code>Task</code> added message
     */
    private String returnAddedMessage(Task task, TaskList taskList) {
        List<Task> savedTasks = taskList.getTasks();
        String message = "";
        message += "I've added this task:\n" + task;
        message += "\nNow you have " + savedTasks.size() + " tasks in the list!";
        return message;
    }
}
