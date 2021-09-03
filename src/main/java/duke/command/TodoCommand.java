package duke.command;

import duke.Storage;
import duke.Ui;
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
    public String execute(TaskList tasks, Ui ui, Storage storage, boolean shouldPrintMessage) throws DukeException {
        Task task = new Todo(description);
        tasks.add(task, storage);
        String message = "Got it. I've added this task:\n  " + task + "\nNow you have " + tasks.toArray().length
            + " task(s) in the list.";
        if (shouldPrintMessage) {
            ui.showMessage(message);
        }
        return message;
    }
}
