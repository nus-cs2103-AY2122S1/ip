package duke.command;

import java.io.FileNotFoundException;

import duke.Storage;
import duke.UI;
import duke.task.TaskList;

public class EventCommand extends Command {

    private String description;
    private String time;

    /**
     * Creates an EventCommand object.
     *
     * @param description The description of the task.
     * @param time The time of the event.
     */
    public EventCommand(String description, String time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws FileNotFoundException {
        tasks.addEvent(description, time, storage, ui);
    }
}
