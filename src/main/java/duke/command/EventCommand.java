package duke.command;
import duke.Storage;
import duke.Ui;
import duke.task.Event;
import duke.task.TaskList;
import java.time.LocalDateTime;

/**
 * This class represents the Command when the user types "event" validly.
 */
public class EventCommand extends Command {
    private String task;
    private LocalDateTime time;

    /**
     * Constructor for EventCommand which takes in the task details and the time of the event.
     *
     * @param task task details.
     * @param time time of the event.
     */
    public EventCommand(String task, LocalDateTime time) {
        this.task = task;
        this.time = time;
    }

    /**
     * Adds an event to the list and saves the task list.
     *
     * @param tasks task list
     * @param storage storage
     * @param ui ui
     * @return output for this command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        Event event = new Event(task, time);
        String output = tasks.add(event, true);
        String saveFileString = tasks.save();
        storage.save(saveFileString);
        return output;
    }
}
