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
    private String eventDescription;

    /**
     * Constructor to intialise an EventCommand.
     *
     * @param eventDescription The event command inputted by the user.
     */
    public EventCommand(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    /**
     * Executes the response to the event command from the user.
     *
     * @param storage The storage Duke uses to save and load the tasklist from.
     * @param taskList The list of tasks Duke needs to execute on.
     * @param ui The Ui Duke utilises to interact with the user.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws IOException, DukeException {

        int pos = eventDescription.indexOf("/");
        String taskDescription = eventDescription.substring(0, pos - 1);
        String eventTime = eventDescription.substring(pos + 4);
        LocalDateTime atDateTime = LocalDateTime.parse(eventTime);

        Event event = new Event(taskDescription, atDateTime);
        taskList.storeTask(event);
        storage.saveFile(taskList.getAllTasks());
        String output = ui.showTaskAdded(event, taskList);
        return output;

    }
}
