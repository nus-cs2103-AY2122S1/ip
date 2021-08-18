package commands;

import core.TaskList;
import tasks.Task;

public class AddTaskCommand extends Command {
    private Task task;

    public AddTaskCommand(String input) {
        task = new Task(input);
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
