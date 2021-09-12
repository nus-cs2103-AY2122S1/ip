package duke.command;

import duke.Ui;
import duke.exception.DukeException;
import duke.exception.TaskExistsException;
import duke.parser.ParsedInput;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents a command to create a todo.
 */
public class TodoCommand implements Command {

    private final String description;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Creates a todo command.
     *
     * @param parsedInput Parsed input
     * @param tasks Task list
     * @param ui Ui
     */
    public TodoCommand(ParsedInput parsedInput, TaskList tasks, Ui ui) {
        this.description = parsedInput.description;
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Runs the todo command.
     *
     * @return Success message
     * @throws DukeException Could not create a todo
     */
    @Override
    public String run() throws DukeException {
        // Extra Functionality: No duplicate tasks
        if (tasks.getTaskIndex(description, Task.TaskTypes.TODO) != -1) {
            throw new TaskExistsException(Task.TaskTypes.TODO, description);
        }

        Task todo = new Todo(description);
        tasks.add(todo);
        return ui.showAddedMessage(todo, tasks.size());
    }
}
