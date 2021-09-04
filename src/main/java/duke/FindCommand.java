package duke;

import java.util.List;
import java.util.stream.Collectors;

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
        List<Task> matchedTasks = savedTasks
                .stream()
                .filter(task -> task.getBody().contains(this.sequence))
                .collect(Collectors.toList());
        for (int i = 0; i < matchedTasks.size(); i++) {
            int label = i + 1;
            message += label + ". " + matchedTasks.get(i) + "\n";
        }
        return message;
    }
}
