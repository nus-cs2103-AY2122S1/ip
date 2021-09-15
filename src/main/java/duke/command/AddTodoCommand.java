package duke.command;

import duke.misc.TaskList;
import duke.misc.Ui;
import duke.task.Task;
import duke.task.Todo;

/**
 * AddTodoCommand class to represent the command to add a Todo task.
 */
public class AddTodoCommand extends Command {
    private boolean isBye = false;
    private String inputSuffix;

    /**
     * Constructor for AddTodoCommand class.
     *
     * @param inputSuffix The task's description.
     */
    public AddTodoCommand(String inputSuffix) {
        this.inputSuffix = inputSuffix;
    }

    /**
     * Executes the operation to add the Todo task.
     *
     * @param tl The TaskList which the task is added to.
     * @return String to notify user of successful command execution.
     */
    public String execute(TaskList tl) {
        assert !inputSuffix.isEmpty();

        Task task = new Todo(inputSuffix);
        return Ui.ADD_MSG + tl.addTask(task);
    }

    public boolean getIsBye() {
        return isBye;
    }
}
