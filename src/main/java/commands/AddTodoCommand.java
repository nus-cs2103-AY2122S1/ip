package commands;

import tasks.Task;
import tasks.TaskList;
import tasks.Todo;

/**
 * A command to add a Todo task to Duke's taskList.
 */
public class AddTodoCommand extends AddCommand {

    private final TaskList taskList;

    /**
     * Creates an AddTodoCommand.
     *
     * @param input The input by the user that triggers this command.
     * @param taskList The taskList for Duke.
     */
    public AddTodoCommand(String input, TaskList taskList) {
        super(input, Task.Type.TODO);
        this.taskList = taskList;
    }

    /**
     * Adds a tasks.Todo event based on the user's input after verifying that the
     * user's input is valid.
     */
    @Override
    public boolean execute() {
        String details = this.removeFirstWordFromInput();
        if (details != null && this.verifyAddCommand(details.trim())) {
            Task task = Todo.newTodoTask(details);
            this.setExecutionMessage(this.taskList.addTask(task));
            return true;
        }
        this.setExecutionMessage(this.getInvalidArgumentsMessage());
        return false;
    }

    @Override
    public boolean verifyAddCommand(String input) {
        return input.length() > 0;
    }
}
