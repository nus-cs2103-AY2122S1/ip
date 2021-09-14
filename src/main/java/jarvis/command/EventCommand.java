package jarvis.command;

import jarvis.exception.JarvisException;
import jarvis.exception.TaskDetailsEmptyException;
import jarvis.message.OutputMessage;
import jarvis.parser.Parser;
import jarvis.storage.Storage;
import jarvis.task.Event;
import jarvis.task.TaskList;
import jarvis.ui.Ui;

/**
 * Encapsulates the event task command
 */
public class EventCommand extends Command {
    private String eventDescription;
    private String eventDate;
    private String eventStartTime;
    private String eventEndTime;

    /**
     * Constructor for EventCommand.
     *
     * @param userInputWithoutCommandTrigger User input without the command trigger.
     * @throws TaskDetailsEmptyException If any of the required details are empty.
     */
    public EventCommand(String userInputWithoutCommandTrigger) throws TaskDetailsEmptyException {
        String[] splitStrings = Parser.getEventInfoArray(userInputWithoutCommandTrigger);
        this.eventDescription = splitStrings[0].trim();
        if (eventDescription.equals("")) {
            throw new TaskDetailsEmptyException("description");
        }
        if (splitStrings.length < 2) {
            throw new TaskDetailsEmptyException("event date and time");
        }
        String[] eventDateTimeArray = Parser.getEventDateTimeArray(splitStrings[1].trim());
        if (eventDateTimeArray.length < 2) {
            throw new TaskDetailsEmptyException("event start and end time");
        }
        if (eventDateTimeArray.length < 3) {
            throw new TaskDetailsEmptyException("event end time");
        }
        this.eventDate = eventDateTimeArray[0].trim();
        this.eventStartTime = eventDateTimeArray[1].trim();
        this.eventEndTime = eventDateTimeArray[2].trim();
    }

    /**
     * Creates the event task, adds to storage file and shows the ui message to user.
     *
     * @param taskList The list in which the tasks are stored.
     * @param storage Storage to save or load tasks to hard-disk.
     * @param ui Ui to show information to the user.
     * @return A OutputMessage that needs to be shown to the user after execution.
     * @throws JarvisException If there is an error.
     */
    @Override
    public OutputMessage execute(TaskList taskList, Storage storage, Ui ui) throws JarvisException {
        Event newEvent = taskList.addEvent(eventDescription, eventDate, eventStartTime, eventEndTime);
        storage.addToStorageFile(newEvent.toStorageFormatString());
        return ui.getTaskAddedMessage(newEvent, taskList);
    }
}
