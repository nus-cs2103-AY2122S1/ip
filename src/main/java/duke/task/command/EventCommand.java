package duke.task.command;

import duke.DukeException;
import duke.TaskList;
import duke.task.Event;

/**
 * Class to implement the event command.
 */
public class EventCommand extends Command {
    private TaskList taskList;
    private String description;
    private String at;

    /**
     * Instantiates a class object.
     *
     * @param taskList the list of tasks
     * @param description the description of the event
     * @param at the time of the event
     */
    public EventCommand(TaskList taskList, String description, String at) {
        this.taskList = taskList;
        this.description = description;
        this.at = at;
    }

    /**
     * Executes the command.
     *
     * @return The output message of the command.
     * @throws DukeException Thrown when a duke exception happens.
     */
    @Override
    public String execute() throws DukeException {
        Event event = new Event(description, at);
        return taskList.addItem(event);
    }
}
