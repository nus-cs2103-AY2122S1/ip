package duke.command;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.task.Event;
import duke.storage.Storage;
import duke.ui.Ui;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private final Event newTask;

    public EventCommand(String description, LocalDateTime at) {
        this(new Event(description, at));
    }

    public EventCommand(Event newTask){
        this.newTask = newTask;
    }

    /**
     * Execute the command to add an Event to the given TaskList.
     *
     * @param tasks   The TaskList of the Duke instance.
     * @param ui      The UI handler of the Duke instance.
     * @param storage The storage handler of the Duke instance.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(newTask);
        storage.update(tasks);
        ui.showFramedMsg("Got it. I've added this task:\n  "
                + newTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}
