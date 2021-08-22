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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> savedTasks = tasks.getTasks();
        int counter = 0;
        for (Task i : savedTasks) {
            if (i instanceof Event) {
                if (((Event) i).getDate().equals(this.date)) {
                    System.out.println(i);
                    counter++;
                }
            }
            if (i instanceof Deadline) {
                if (((Deadline) i).getDeadline().equals(this.date)) {
                    System.out.println(i);
                    counter++;
                }
            }
        }
        if (counter == 0) {
            System.out.println("No tasks found!");
        }
    }
}
