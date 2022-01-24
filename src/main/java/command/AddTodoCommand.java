package command;

import duke.Storage;
import duke.Ui;
import task.TaskList;
import task.Todo;

/**
 * Represents a command to add a todo.
 */
public class AddTodoCommand extends AddCommand {

    private String desc;

    /**
     * Constructs a new AddTodoCommand object.
     *
     * @param desc description of the todo.
     */
    public AddTodoCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Executes the AddTodoCommand.
     *
     * @param tasks Current TaskList.
     * @param ui Ui object.
     * @param storage Storage object.
     * @return response from Duke.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // tasks
        Todo newTodo = new Todo(desc);
        tasks.add(newTodo);
        // ui
        String response = respond(newTodo, tasks.size());
        String result = ui.showResponse(response);
        // storage
        storage.save(tasks);

        return result;
    }
}
