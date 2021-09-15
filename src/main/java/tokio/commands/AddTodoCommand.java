package tokio.commands;

import java.io.IOException;

import tokio.exceptions.DukeException;
import tokio.storage.Storage;
import tokio.tasks.TaskList;
import tokio.tasks.Todos;
import tokio.ui.Ui;

/**
 * Adds todo task into task list and storage.
 */
public class AddTodoCommand extends Command {
    protected String description;

    /**
     * Constructor for AddTodoCommand
     *
     * @param description Name of Todo task.
     */
    public AddTodoCommand(String description) {
        this.description = description.trim();
    }

    /**
     * Executes the adding of todo task in the task list and storage file.
     *
     * @param tasks Existing tasks in the task list.
     * @param ui User input format.
     * @param storage Stores created command into the txt file
     * @return String message for Tokio's reply.
     * @throws IOException If task cannot be written to the storage file.
     * @throws DukeException If task already exists.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        Todos addTodo = new Todos(description);
        if (!tasks.addTask(addTodo)) {
            throw new DukeException("Oops Rio, this todo task already exists!\n");
        }
        storage.writeTask(addTodo);
        return ui.printAddCommand(addTodo, tasks);
    }

    /**
     * Indicates that the add todo command is non-terminating.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
