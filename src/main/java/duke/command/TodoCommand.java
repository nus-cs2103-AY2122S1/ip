package duke.command;

import duke.Duke;
import duke.Storage;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * TodoCommand class encapsulates command to add a new todo.
 */
public class TodoCommand extends AddCommand {
    /**
     * Constructs a TodoCommand with the specified description.
     *
     * @param description Description of the command.
     */
    public TodoCommand(String description) {
        super(description);
    }

    @Override
    public void execute(Duke duke, TaskList tasks, Storage storage) throws DukeException {
        Task task = new Todo(description);
        tasks.add(task, storage);
        String message = "Got it. I've added this task:\n  " + task + "\nNow you have " + tasks.toArray().length
            + " task(s) in the list.";
        duke.setResponse(message);
    }
}
