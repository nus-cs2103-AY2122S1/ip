package duke.command;

import java.io.IOException;

import duke.misc.TaskList;
import duke.misc.Ui;
import duke.task.Task;
import duke.task.Todo;

/**
 * AddTodoCommand class to represent the command to add a Todo task.
 */
public class AddTodoCommand extends Command {
    private final boolean isBye;
    private final String inputSuffix;

    /**
     * Constructor for AddTodoCommand class.
     *
     * @param inputSuffix The task's description.
     */
    public AddTodoCommand(String inputSuffix) {
        this.inputSuffix = inputSuffix;
        isBye = false;
    }

    /**
     * Executes the operation to add the Todo task.
     *
     * @param tl The current TaskList.
     * @return String to notify user of successful command execution.
     * @throws IOException In case of invalid file directory.
     */
    public String execute(TaskList tl) throws IOException {
        assert !inputSuffix.isEmpty();

        Task task = new Todo(inputSuffix);
        return Ui.ADD_MSG
                + tl.addTask(task)
                + "Current tasks count: "
                + tl.getSize()
                + "\n";
    }

    public boolean getIsBye() {
        return isBye;
    }
}
