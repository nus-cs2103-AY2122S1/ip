package duke.command;

import duke.util.TaskList;
import duke.util.Ui;

/**
 * Class that represents the Command to add to the TaskList.
 *
 * @author Benedict Chua
 */
public class AddCommand extends Command {
    private TaskList taskList;
    private String task;
    private String type;

    public AddCommand(TaskList taskList, String task, String type) {
        this.taskList = taskList;
        this.task = task;
        this.type = type;
    }

    @Override
    public void execute() {
        Ui.displayMessage(this.taskList.addToList(this.task, this.type));
    }
}