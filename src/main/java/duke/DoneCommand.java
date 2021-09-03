package duke;

import java.util.List;

/**
 * Represents a command to mark task as done
 */
public class DoneCommand extends Command {
    private final int index;

    /**
     * Constructs an instance of <code>DoneCommand</code>
     * @param index Index to be marked as done
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes <code>DoneCommand</code>
     * @param tasks <code>TaskList</code> containing saved tasks
     * @param ui <code>Ui</code> responsible for user interactions
     * @param storage <code>Storage</code> responsible for saving tasks to drive
     * @return corresponding message
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> savedTasks = tasks.getTasks();
        String message = "";
        if (this.index > savedTasks.size() || this.index < 1) {
            DukeException exception = new DukeException("Number out of range!");
            System.out.println(exception);
            return exception.getMessage();
        }
        Task oldTask = savedTasks.get(this.index - 1);
        Task newTask = oldTask.setDone();
        tasks.replaceTask(this.index - 1, newTask);
        message += "Nice! I've marked this task as done:\n" + newTask;
        return message;
    }
}
