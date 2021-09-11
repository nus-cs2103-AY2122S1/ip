package tokio.commands;

import java.io.IOException;

import tokio.exceptions.DukeException;
import tokio.storage.Storage;
import tokio.tasks.Events;
import tokio.tasks.TaskList;
import tokio.ui.Ui;

public class AddEventCommand extends Command {
    protected String description;
    
    public AddEventCommand(String description) {
        this.description = description;
    }
    
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        String[] descTimeArray = description.split("/at ");
        if (descTimeArray.length < 2) {
            return ui.printSomethingMissing("event");
        }
        String eventDesc = descTimeArray[0].trim();
        String[] dateTimeArray = descTimeArray[1].split(" ");
        if (dateTimeArray.length < 2) {
            return ui.printSomethingMissing("event");
        }
        String eventDate = dateTimeArray[0].trim();
        String eventTime = dateTimeArray[1].trim();
        Events addEvent = new Events(eventDesc, eventDate, eventTime);
        if (!tasks.addTask(addEvent)) {
            return ui.printDuplicateTask();
        }
        storage.writeTask(addEvent);
        return ui.printAddCommand(addEvent, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
