package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.task.Todo;

public class TodoCommand extends Command {
    private Todo todoTask;

    public TodoCommand(String taskName) {
        todoTask = new Todo(taskName);
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        taskList.addTask(todoTask);
        storage.addTasksToFile(taskList);
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
