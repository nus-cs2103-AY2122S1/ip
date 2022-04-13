package duke.command;

import duke.data.TaskList;
import duke.data.task.ToDo;
import duke.storage.Storage;

/**
 * This class abstracts the ToDo command that the user wants to execute.
 */
public class ToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    protected final ToDo newTask;

    /**
     * Constructs an ToDoCommand that will produce the given ToDo Task once executed.
     *
     * @param newTask The Event task to be generated.
     */
    public ToDoCommand(ToDo newTask) {
        this.newTask = newTask;
    }

    /**
     * Execute the command to add a task to the given TaskList.
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
                + newTask
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
