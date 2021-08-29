package duke.command;

import java.io.FileNotFoundException;

import duke.ResponseLogic;
import duke.Storage;
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
    public String execute(TaskList tasks, ResponseLogic responseLogic, Storage storage) throws FileNotFoundException {
        return tasks.addEvent(description, time, storage, responseLogic);
    }
}
