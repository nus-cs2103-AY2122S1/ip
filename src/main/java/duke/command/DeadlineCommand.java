package duke.command;

import duke.data.TaskList;
import duke.data.task.Deadline;
import duke.storage.Storage;

/**
 * This class abstracts the deadline command that the user wants to execute.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private final Deadline newTask;

    /**
     * Constructs a DeadlineCommand that will produce the given Deadline Task once executed.
     *
     * @param newTask The Deadline task to be generated.
     */
    public DeadlineCommand(Deadline newTask) {
        assert newTask != null;
        this.newTask = newTask;
    }

    /**
     * Execute the command to add a new Deadline to the given TaskList.
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
