package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

public class AddTodoCommand extends Command {
    private String description;

    /**
     * Creates a command that adds a {@link Todo} to the user's tasks.
     *
     * @param arguments Command arguments.
     */
    public AddTodoCommand(String arguments) throws DukeException {
        String description = arguments.trim();
        if (description.length() == 0) {
            throw new InvalidCommandException("Todo must have description");
        }

        this.description = description;
    }

    /**
     * Adds a todo to the user's tasklist.
     *
     * @param taskList The tasklist.
     * @param ui The instance of the {@link Ui} class.
     * @param storage The instance of the {@link Storage} class.
     * @throws IOException when unable to save tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws Exception {
        Task todo = new Todo(description);
        taskList.addTask(todo);

        storage.saveTasks(taskList);
        ui.printMessage("Added the following task:\n    " + todo.toString() + "\n" + "You now have "
                + taskList.size() + " tasks in your list.");
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
