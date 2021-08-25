package duke.commands;

import duke.DukeException;
import duke.Tasklist;
import duke.UI;
import duke.PersistentStorage;
import duke.tasks.Event;

import java.time.LocalDateTime;

/**
 * Class encapsulating an "event" command from the user
 */
public class EventCommand extends Command {

    /** A String description of the Event by the user */
    private String description;

    /** A LocalDateTime representing the datetime of the Event */
    private LocalDateTime eventDateTime;

    /**
     * A constructor for an EventCommand.
     *
     * @param description The String description of the Event by the user.
     * @param eventDateTime The LocalDateTime representing the datetime of the event.
     */
    public EventCommand(String description, LocalDateTime eventDateTime) {
        this.description = description;
        this.eventDateTime = eventDateTime;
    }

    /**
     * Executes the event command by creating the specified Event, adding it to the
     * Tasklist and displaying the updated Tasklist.
     *
     * @param taskList The Tasklist associated with the Duke instance.
     * @param ui The UI associated with the Duke instance.
     * @param storage The PersistentStorage associated with the Duke instance.
     */
    public void executeCommand(Tasklist taskList, UI ui, PersistentStorage storage) {
        Event event = new Event(this.description, this.eventDateTime);

        taskList.addTask(event);
        ui.showAddedTask(taskList, event);
    }
}
