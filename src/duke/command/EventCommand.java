package duke.command;

import duke.data.TaskList;
import duke.data.task.Event;
import duke.storage.Storage;
import duke.ui.Ui;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    protected final Event newTask;

    public EventCommand(String description, LocalDateTime at) {
        this(new Event(description, at));
    }

    public EventCommand(Event newTask){
        this.newTask = newTask;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(newTask);
        storage.update(tasks);
        ui.showFramedMsg("Got it. I've added this task:\n  "
                + newTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}
