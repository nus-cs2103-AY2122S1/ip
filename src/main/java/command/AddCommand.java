package command;

import duke.TaskList;
import duke.Ui;
import task.Task;

public class AddCommand extends Command {
    private final Task newTask;

    public AddCommand(Task newTask) {
        super();
        this.newTask = newTask;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        taskList.addTask(newTask);
    }
}
