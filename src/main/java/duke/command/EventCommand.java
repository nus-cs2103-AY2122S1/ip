package duke.command;

import duke.DukeException;
import duke.Event;
import duke.TaskList;

/**
 * The command to add a Event typed task.
 */
public class EventCommand extends Command {

    private String description;
    private String date;
    private TaskList taskList;

    /**
     * Constructor for EventCommand.
     * @param description The description of the task.
     * @param date The event date.
     * @param taskList The list of tasks.
     */
    public EventCommand(String description, String date, TaskList taskList) {
        this.taskList = taskList;
        this.date = date;
        this.description = description;
    }

    /**
     * Returns an output message after executing the event command.
     * @return The output message of the command.
     * @throws DukeException Thrown when a duke exception happens.
     */
    @Override
    public String execute() throws DukeException {
        Event event = new Event(description, date);
        return taskList.addItem(event);
    }
}
