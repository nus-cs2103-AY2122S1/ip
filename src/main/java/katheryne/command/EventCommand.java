package katheryne.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import katheryne.KatheryneException;
import katheryne.Message;
import katheryne.Storage;
import katheryne.TaskList;
import katheryne.Ui;
import katheryne.task.Event;

/**
 * Command to add an event to the tasklist
 */
public class EventCommand extends Command {
    /**
     * The constant name to refer to this command by
     */
    public static final String COMMAND = "EVENT";
    private final String description;
    private final LocalDate atAsDate;

    /**
     * Constructs the EventCommand by putting the processedRemainingText into appropriate variables.
     *
     * @param processedRemainingText The first item is the description; the second is the time the event is
     * @throws KatheryneException If the date is in the wrong format
     */
    EventCommand(String[] processedRemainingText) throws KatheryneException {
        this.description = processedRemainingText[0];
        try {
            this.atAsDate = LocalDate.parse(processedRemainingText[1]);
        } catch (DateTimeParseException e) {
            throw new KatheryneException(
                    "The at time is in the wrong format. It must be in the format YYYY-MM-DD");
        }
    }

    @Override public String getResponse(TaskList taskList, Storage storage) throws KatheryneException {
        Event event = new Event(description, atAsDate);
        taskList.add(event);
        return Message.addEvent(event, taskList);
    }

    /**
     * Adds an event to the task list, prints a confirmation message.
     *
     * @param taskList A container for tasks which contains Katheryne's tasks.
     * @param ui The Ui used for the user interface.
     * @param storage The storage object taking care of writing and reading the text file.
     * @throws KatheryneException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws KatheryneException {
        Event event = new Event(description, atAsDate);
        taskList.add(event);
        ui.say("Event '" + description + "' added to your list, scheduled for "
                + event.getStringAt());
        ui.countTasksInList(taskList);
    }

}
