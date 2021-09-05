package duke.commands;

import duke.DukeException;
import duke.PersistentStorage;
import duke.Response;
import duke.Tasklist;
import duke.tasks.ToDo;

/**
 * Class encapsulating a "todo" command by the user.
 */
public class TodoCommand extends Command {

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
    public CommandResult executeCommand(Tasklist taskList, Response response, PersistentStorage storage) {
        ToDo task = new ToDo(this.description);

        taskList.addTask(task);
        return new CommandResult(response.showAddedTask(taskList, task));
    }
}
