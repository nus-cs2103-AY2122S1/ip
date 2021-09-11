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

    public EventCommand(String description, String date, TaskList taskList) {
        this.taskList = taskList;
        this.date = date;
        this.description = description;
    }

    @Override
    public String execute() throws DukeException {
        Event event   = new Event(description, date);
        return taskList.addItem(event);
    }
}
