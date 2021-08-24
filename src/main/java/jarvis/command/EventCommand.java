package jarvis.command;

import jarvis.exception.StorageFileException;
import jarvis.exception.TaskDetailsEmptyException;
import jarvis.parser.Parser;
import jarvis.storage.Storage;
import jarvis.task.Event;
import jarvis.task.TaskList;
import jarvis.ui.Ui;

public class EventCommand extends Command {
    private String eventDescription;
    private String eventTime;

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

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws StorageFileException {
        Event newEvent = taskList.addEvent(eventDescription, eventTime);
        storage.addToStorageFile(newEvent.toStorageFormatString());
        ui.showTaskAddedMessage(newEvent, taskList);
    }
}
