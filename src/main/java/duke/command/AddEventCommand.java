package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;

/**
 * Encapsulates the add event command.
 */
public class AddEventCommand implements Command {
    private String eventDesc;
    private String eventDateTime;

    /**
     * Constructor for an AddEventCommand instance.
     *
     * @param eventDesc Event description as entered by user.
     * @param  eventDateTime Event date and time as entered by user.
     */
    public AddEventCommand(String eventDesc, String eventDateTime) {
        this.eventDesc = eventDesc;
        this.eventDateTime = eventDateTime;
    }

    /**
     * Creates a new task from user's input and adds task to the given task list.
     * @param tasks TaskList instance which the new task is to be added to.
     * @param ui Duke's UI.
     * @return The String representation of Duke's response.
     * @throws DukeException For invalid inputs.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) throws DukeException {
        Task t = new Event(eventDesc, eventDateTime);
        tasks.add(t);
        return ui.formatDoneAddingTaskMsg(tasks, t);
    }

    /**
     * Indicates if the command is an exit command.
     *
     * @return If the command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
