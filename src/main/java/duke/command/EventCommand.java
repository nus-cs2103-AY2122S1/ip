package duke.command;

import duke.task.Event;
import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Represents the command equivalent of the Event task.
 */
public class EventCommand extends Command {
    protected static final String COMMAND = "event";
    private Event event;

    protected EventCommand(String remainingText) throws DukeException {
        event = Event.createNewEvent(remainingText, false);
    }

    /**
     * Executes the command.
     *
     * @param taskList The taskList keeping track of the tasks.
     * @param ui The Ui used for the user interface.
     * @param storage The storage object taking care of writing and reading the text file.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert event != null : "event should not be null";
        return taskList.addTask(event);
    }
}
