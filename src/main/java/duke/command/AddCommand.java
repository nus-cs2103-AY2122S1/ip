package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Add command instructions to add task into the program.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * constructor for Adding.
     *
     * @param input String command.
     * @param task Task object to be added.
     */
    public AddCommand(String input, Task task) {
        super(input);
        this.task = task;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        return ui.taskAddedMessage(task, taskList.getTotalNumberOfTask());
    }


}
