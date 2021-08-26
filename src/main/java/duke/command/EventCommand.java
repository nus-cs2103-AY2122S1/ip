package duke.command;

import java.io.IOException;
import java.time.LocalDateTime;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * The EventCommand class encapsulates the execution of the event command from the user.
 */
public class EventCommand extends Command {
    /** The event command inputted by the user. */
    String fullCommand;

    /**
     * Constructor to intialise an EventCommand.
     * @param fullCommand The event command inputted by the user.
     */
    public EventCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the response to the event command from the user.
     * @param storage The storage Duke uses to save and load the tasklist from.
     * @param taskList The list of tasks Duke needs to execute on.
     * @param ui The Ui Duke utilises to interact with the user.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws IOException, DukeException {
        try {
            if (fullCommand.equals("event")) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }

            String eventDescription = fullCommand.split(" ", 2)[1];
            int pos = eventDescription.indexOf("/");
            String description = eventDescription.substring(0, pos - 1);
            String at = eventDescription.substring(pos + 4);
            LocalDateTime atDateTime = LocalDateTime.parse(at);

            Event event = new Event(description, atDateTime);
            taskList.storeTask(event);
            storage.saveFile(taskList.getAllTasks());
            ui.showTaskAdded(event, taskList);

        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}
