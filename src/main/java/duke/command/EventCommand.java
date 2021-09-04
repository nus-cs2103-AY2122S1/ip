package duke.command;

import duke.data.TaskList;
import duke.data.task.Event;
import duke.storage.Storage;

/**
 * This class abstracts the event command that the user wants to execute.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private final Event newTask;

    /**
     * Constructs an EventCommand that will produce the given Event Task once executed.
     *
     * @param newTask The Event task to be generated.
     */
    public EventCommand(Event newTask) {
        this.newTask = newTask;
    }

    /**
     * Execute the command to add an Event to the given TaskList.
     *
     * @param tasks   The TaskList of the Duke instance.
     * @param storage The storage handler of the Duke instance.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert newTask != null;
        tasks.add(newTask);
        storage.update(tasks);
        return "Got it. I've added this task:\n  "
                + newTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
