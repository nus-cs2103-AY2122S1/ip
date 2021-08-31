package jarvis.command;

import jarvis.exception.StorageFileException;
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
    private String eventTime;

    /**
     * Constructor for EventCommand
     *
     * @param userInputWithoutCommandTrigger User input without the command trigger
     * @throws TaskDetailsEmptyException If any of the required details are empty
     */
    public EventCommand(String userInputWithoutCommandTrigger) throws TaskDetailsEmptyException {
        String[] splitStrings = Parser.getEventInfoArray(userInputWithoutCommandTrigger);
        this.eventDescription = splitStrings[0].trim();
        if (eventDescription.equals("")) {
            throw new TaskDetailsEmptyException("description");
        }
        if (splitStrings.length < 2) {
            throw new TaskDetailsEmptyException("event time");
        }
        this.eventTime = splitStrings[1].trim();
    }

    /**
     * Creates the event task, adds to storage file and shows the ui message to user
     *
     * @param taskList The list in which the tasks are stored
     * @param storage Storage to save or load tasks to hard-disk
     * @param ui Ui to show information to the user
     * @return A OutputMessage that needs to be shown to the user after execution
     * @throws StorageFileException If there is an error when writing to storage file
     */
    @Override
    public OutputMessage execute(TaskList taskList, Storage storage, Ui ui) throws StorageFileException {
        Event newEvent = taskList.addEvent(eventDescription, eventTime);
        storage.addToStorageFile(newEvent.toStorageFormatString());
        return ui.getTaskAddedMessage(newEvent, taskList);
    }
}
