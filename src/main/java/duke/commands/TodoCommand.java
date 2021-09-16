package duke.commands;

import duke.DukeException;
import duke.tasks.ToDo;
import duke.util.PersistentStorage;
import duke.util.Response;
import duke.util.Tasklist;

/**
 * Class encapsulating a "todo" command by the user.
 */
public class TodoCommand extends Command {

    /** Starting index of a ToDo description */
    public static final int TODO_DESC_START = 5;

    /** A String description of the ToDo */
    private String description;

    /**
     * A constructor for a TodoCommand
     *
     * @param description A String describing the ToDo
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the todo command by creating the specified ToDo, adding it to the
     * Tasklist and displaying the updated Tasklist.
     *
     * @param taskList The Tasklist associated with the Duke instance.
     * @param response The UI associated with the Duke instance.
     * @param storage The PersistentStorage associated with the Duke instance.
     * @return A CommandResult detailing the result of adding a ToDo task.
     * @throws DukeException if the provided target index is not in range.
     */
    public CommandResult executeCommand(Tasklist taskList, Response response, PersistentStorage storage)
            throws DukeException {
        ToDo task = new ToDo(this.description);
        taskList.addTask(task);
        storage.saveTasks(taskList);
        return new CommandResult(response.showAddedTask(taskList, task));
    }
}
