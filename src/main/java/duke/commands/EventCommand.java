package duke.commands;

import java.time.LocalDateTime;

import duke.PersistentStorage;
import duke.Response;
import duke.Tasklist;
import duke.tasks.Event;

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
     * @param response The UI associated with the Duke instance.
     * @param storage The PersistentStorage associated with the Duke instance.
     * @return A CommandResult detailing the addition of an Event task.
     */
    public CommandResult executeCommand(Tasklist taskList, Response response, PersistentStorage storage) {
        Event event = new Event(this.description, this.eventDateTime);

        taskList.addTask(event);
        return new CommandResult(response.showAddedTask(taskList, event));
    }
}
