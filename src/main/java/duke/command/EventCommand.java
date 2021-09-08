package duke.command;

import duke.task.Event;
import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;

public class EventCommand extends Command {
    protected static final String COMMAND = "event";
    private Event event;

    protected EventCommand(String remainingText) throws DukeException {
        event = Event.createNewEvent(remainingText, false);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert event != null : "event should not be null";
        return taskList.addTask(event);
    }
}
