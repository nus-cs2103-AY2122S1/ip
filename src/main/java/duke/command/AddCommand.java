package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class AddCommand extends Command{

    private Task task;

    public AddCommand(String input, Task task) {
        super(input);
        this.task = task;
    }

    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        ui.taskAddedMessage(task, taskList.getTotalNumberOfTask());
        return true;
    }


}
