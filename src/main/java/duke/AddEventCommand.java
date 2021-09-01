package duke;

import java.io.IOException;
import java.util.List;

/**
 * Represents a command to add an event
 */
public class AddEventCommand extends Command {
    private final Event event;

    /**
     * Constructs an instance of <code>AddEventCommand</code>
     * @param event <code>Event</code> object
     */
    public AddEventCommand(Event event) {
        this.event = event;
    }

    /**
     * Executes <code>AddEventCommand</code>
     * @param tasks <code>TaskList</code> containing saved tasks
     * @param ui <code>Ui</code> responsible for user interactions
     * @param storage <code>Storage</code> responsible for saving tasks to drive
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.event);
        return returnAddedMessage(this.event, tasks);
    }

    private String returnAddedMessage(Task task, TaskList taskList) {
        List<Task> savedTasks = taskList.getTasks();
        String message = "";
        message += "I've added this task:\n" + task;
        message += "\nNow you have " + savedTasks.size() + " tasks in the list!";
        return message;
    }
}
