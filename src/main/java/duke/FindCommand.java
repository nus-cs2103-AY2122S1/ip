package duke;

import java.util.List;

/**
 * Represents a command to get all tasks that matches the sequence
 */
public class FindCommand extends Command {
    private final String sequence;

    /**
     * Constructs an instance of <code>FindCommand</code>
     * @param sequence the sequence to match
     */
    public FindCommand(String sequence) {
        this.sequence = sequence;
    }

    /**
     * Executes <code>FindCommand</code>
     * @param tasks <code>TaskList</code> containing saved tasks
     * @param ui <code>Ui</code> responsible for user interactions
     * @param storage <code>Storage</code> responsible for saving tasks to drive
     * @return corresponding message including matching tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> savedTasks = tasks.getTasks();
        String message = "Here are the matching tasks in your list:\n";
        int counter = 1;
        for (Task i : savedTasks) {
            if (i.getBody().contains(this.sequence)) {
                message += counter + ". " + i + "\n";
                counter++;
            }
        }
        return message;
    }
}
