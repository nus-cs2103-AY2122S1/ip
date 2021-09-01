package duke;

import java.util.List;
import java.time.LocalDate;

/**
 * Represents a command to get all tasks on a specified date
 */
public class GetCommand extends Command {
    private final LocalDate date;

    /**
     * Constructs an instance of <code>GetCommand</code>
     * @param date
     */
    public GetCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Executes <code>GetCommand</code>
     * @param tasks <code>TaskList</code> containing saved tasks
     * @param ui <code>Ui</code> responsible for user interactions
     * @param storage <code>Storage</code> responsible for saving tasks to drive
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> savedTasks = tasks.getTasks();
        int counter = 0;
        String message = "Here are the matching tasks in your list:\n";
        for (Task i : savedTasks) {
            if (i instanceof Event) {
                if (((Event) i).getDate().equals(this.date)) {
                    message += i + "\n";
                    counter++;
                }
            }
            if (i instanceof Deadline) {
                if (((Deadline) i).getDeadline().equals(this.date)) {
                    message += i + "\n";
                    counter++;
                }
            }
        }
        if (counter == 0) {
            return "No tasks found!";
        }
        return message;
    }
}
