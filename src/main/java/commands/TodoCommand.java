package commands;

import core.TaskList;
import tasks.Todo;

public class TodoCommand extends Command {
    private Todo todoTask;

    public TodoCommand(String taskName) {
        todoTask = new Todo(taskName);
    }
    @Override
    public void execute(TaskList taskList) {
        taskList.addTask(todoTask);
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
