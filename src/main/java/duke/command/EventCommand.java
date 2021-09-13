package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Event;
import duke.task.TaskList;

public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";

    public static final String MISSING_DESCRIPTION_MESSAGE =
            "The description of the event task cannot be empty";

    private String description;
    private String dateAndTime;

    public EventCommand(String description, String dateAndTime) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        Event newEvent = new Event(description, dateAndTime);
        tasks.addTask(newEvent);
        storage.save(tasks);
        return Ui.getAddedMessage(newEvent, tasks.size());
    }
}
