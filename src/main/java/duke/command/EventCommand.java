package duke.command;

import duke.task.Event;
import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;

public class EventCommand extends Command {
    protected static final String COMMAND = "event";
    private final Event event;

    protected EventCommand(String remainingText) throws DukeException {
        event = Event.newEvent(remainingText, false);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Ui.displayMessage(taskList.addTask(event));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
