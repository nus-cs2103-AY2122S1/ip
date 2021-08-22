package duke;

import java.util.List;

/**
 * Represents a command to delete a task
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs an instance of <code>DeleteCommand</code>
     * @param index Index to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes <code>DeleteCommand</code>
     * @param tasks <code>TaskList</code> containing saved tasks
     * @param ui <code>Ui</code> responsible for user interactions
     * @param storage <code>Storage</code> responsible for saving tasks to drive
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> savedTasks = tasks.getTasks();
        if (this.index > savedTasks.size() || this.index < 1) {
            DukeException exception = new DukeException("Number out of range!");
            System.out.println(exception);
        }
        else {
            Task removedTask = savedTasks.get(this.index-1);
            tasks.deleteTask(this.index-1);
            System.out.println("Alright! I've removed this task:\n" + removedTask);
            System.out.println(String.format("Now you have %d tasks left in the list!", savedTasks.size()));
        }
    }
}
