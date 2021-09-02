package duke;

import java.util.List;

/**
 * Represents a command to add a todo
 */
public class AddTodoCommand extends Command {
    private final Todo todo;

    /**
     * Constructs an instance of <code>AddTodoCommand</code>
     * @param todo
     */
    public AddTodoCommand(Todo todo) {
        this.todo = todo;
    }

    /**
     * Executes <code>AddTodoCommand</code>
     * @param tasks <code>TaskList</code> containing saved tasks
     * @param ui <code>Ui</code> responsible for user interactions
     * @param storage <code>Storage</code> responsible for saving tasks to drive
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.todo);
        return returnAddedMessage(this.todo, tasks);
    }

    private String returnAddedMessage(Task task, TaskList taskList) {
        List<Task> savedTasks = taskList.getTasks();
        String message = "";
        message += "I've added this task:\n" + task;
        message += "\nNow you have " + savedTasks.size() + " tasks in the list!";
        return message;
    }
}
