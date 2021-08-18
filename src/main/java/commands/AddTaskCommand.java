package commands;

import core.TaskList;
import gui.Ui;
import tasks.Task;

public class AddTaskCommand extends Command {
    private Task task;
    private Ui ui;

    public AddTaskCommand(String input) {
        task = new Task(input);
        ui = new Ui();
    }

    @Override
    public void execute(TaskList taskList) {
        taskList.addTask(task);
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
