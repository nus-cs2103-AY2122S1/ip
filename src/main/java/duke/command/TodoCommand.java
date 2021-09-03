package duke.command;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a todo command.
 */
public class TodoCommand extends Command {

    /** The todo message to be added. */
    private final String todo;

    /**
     * Constructor for TodoCommand.
     *
     * @param todo message to be added.
     */
    public TodoCommand(String todo) {
        this.todo = todo;
    }

    /**
     * Executes the command.
     *
     * @param tasks list of tasks.
     * @param ui ui to handle user interaction.
     * @param storage handles reading and writing of data file.
     * @return the appropriate message for adding a todo.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.addTodo(todo);
    }
}
