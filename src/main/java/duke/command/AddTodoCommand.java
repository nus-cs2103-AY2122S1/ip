package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;
import duke.task.Todo;

public class AddTodoCommand extends Command {
    private String arguments;

    /**
     * Creates a command that adds a {@link Todo} to the user's tasks.
     * @param arguments Command arguments.
     */
    public AddTodoCommand(String arguments) throws Exception {
        if (arguments.length() == 0) {
            throw new Exception("Command `todo` requires arguments");
        }
        this.arguments = arguments;
    }

    /**
     * Adds a todo to the user's tasklist.
     * @param taskList The tasklist.
     * @param ui The instance of the {@link Ui} class.
     * @param storage The instance of the {@link Storage} class.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws Exception {
        Task todo = Todo.fromInput(arguments);
        taskList.addTask(todo);

        storage.saveTasks(taskList);
        ui.printMessage("Added the following task:\n    " + todo.toString() + "\n" + "You now have "
                + taskList.size() + " tasks in your list.");
    }

    public boolean shouldExit() {
        return false;
    }
}
