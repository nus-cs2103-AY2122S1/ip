package duke;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a command to get all tasks on a specified date
 */
public class GetCommand extends Command {
    private final LocalDate date;

    /**
     * Constructs an instance of <code>GetCommand</code>
     * @param date the date to match
     */
    public GetCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Executes <code>GetCommand</code>
     * @param tasks <code>TaskList</code> containing saved tasks
     * @param ui <code>Ui</code> responsible for user interactions
     * @param storage <code>Storage</code> responsible for saving tasks to drive
     * @return corresponding message including the matching tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> savedTasks = tasks.getTasks();
        int counter = 0;
        StringBuilder message = new StringBuilder("Here are the matching tasks in your list:\n");
        for (Task i : savedTasks) {
            if (i instanceof Event) {
                if (((Event) i).getDate().equals(this.date)) {
                    message.append(i).append("\n");
                    counter++;
                }
            }
            if (i instanceof Deadline) {
                if (((Deadline) i).getDeadline().equals(this.date)) {
                    message.append(i).append("\n");
                    counter++;
                }
            }
        }
        if (counter == 0) {
            return "No tasks found!";
        }
        return message.toString();
    }
}
