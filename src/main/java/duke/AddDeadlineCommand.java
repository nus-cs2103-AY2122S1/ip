package duke;

import java.io.IOException;

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
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.deadline);
        ui.showAddedMessage(this.deadline, tasks);
    }
}
