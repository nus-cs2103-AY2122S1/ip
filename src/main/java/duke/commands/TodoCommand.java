package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Todo;

/**
 * The TodoCommand class handles when a todo is entered by a user.
 */
public class TodoCommand extends Command {
    private final Todo todo;

    /**
     * Construct a TodoCommand object with the given description.
     *
     * @param description The description of the TodoCommand object.
     * @throws DukeException If the description is empty.
     */
    public TodoCommand(String description) throws DukeException {
        if (description.trim().equals("")) {
            throw new DukeException("Todos can't be empty!");
        }
        this.todo = new Todo(description.trim());
    }

    /**
     * Executes the addition of a Todo to the TaskList.
     *
     * @param taskList The current TaskList being used.
     * @param ui The current Ui being used.
     * @param storage The current Storage being used.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(todo);
        storage.write(taskList);
        return ui.showTaskAdded(todo, taskList);
    }

}
