package tokio.commands;

import java.io.IOException;

import tokio.exceptions.DukeException;
import tokio.storage.Storage;
import tokio.tasks.Events;
import tokio.tasks.TaskList;
import tokio.ui.Ui;

/**
 * Adds event to task list and storage file.
 */
public class AddEventCommand extends Command {
    protected String description;

    /**
     * Constructor for AddEventCommand.
     *
     * @param description Name, date and time of event task.
     */
    public AddEventCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the adding of event task in the task list and storage file.
     *
     * @param tasks Existing tasks in the task list.
     * @param ui User input format.
     * @param storage Stores created command into the txt file
     * @return String message for Tokio's reply.
     * @throws IOException If task cannot be written to the storage file.
     * @throws DukeException If task's date or time format is wrong or if task already exists.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        String[] descTimeArray = description.split("/at ");
        if (descTimeArray.length < 2) {
            throw new DukeException("Something is missing from this event\n" + "Rio, please follow this format:\n"
                    + "event {name} /at {yyyy-MM-dd} {HH:mm}");
        }
        String eventDesc = descTimeArray[0].trim();
        String[] dateTimeArray = descTimeArray[1].split(" ");
        if (dateTimeArray.length < 2) {
            throw new DukeException("Something is missing from this event\n" + "Rio, please follow this format:\n"
                    + "event {name} /by {yyyy-MM-dd} {HH:mm}");
        }
        String eventDate = dateTimeArray[0].trim();
        String eventTime = dateTimeArray[1].trim();
        Events addEvent = new Events(eventDesc, eventDate, eventTime);
        if (!tasks.addTask(addEvent)) {
            throw new DukeException("Oops Rio, this event task already exists!\n");
        }
        storage.writeTask(addEvent);
        return ui.printAddCommand(addEvent, tasks);
    }

    /**
     * Indicates that AddEventCommand is non-terminating.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
