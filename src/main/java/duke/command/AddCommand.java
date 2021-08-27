package duke.command;

import duke.util.TaskList;
import duke.util.Ui;

/**
 * Class that represents the Command to add to the TaskList.
 *
 * @author Benedict Chua
 */
public class AddCommand extends Command {
    private TaskList tasks;
    private String task;
    private String taskType;

    public AddCommand(TaskList taskList, String task, String taskType) {
        this.tasks = taskList;
        this.task = task;
        this.taskType = taskType;
    }

    @Override
    public void execute() {
        Ui.displayMessage(this.tasks.addToList(this.task, this.taskType));
    }
}