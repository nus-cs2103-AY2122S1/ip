package aisu.command;

import aisu.*;
import aisu.task.Task;
import aisu.task.Todo;

public class AddCommand extends Command {
    private final TaskList.TaskTypes taskType;
    private final String input;

    public AddCommand(TaskList.TaskTypes taskType, String input) {
        this.taskType = taskType;
        this.input = input;
    }

    @Override
    public void execute(TaskList tasklist, Storage storage, Ui ui) throws AisuException {
        Task newTask = new Todo("dummy");
        switch (this.taskType) {
        case T:
            newTask = tasklist.addTask(this.input, TaskList.TaskTypes.T);
            break;
        case D:
            newTask = tasklist.addTask(this.input, TaskList.TaskTypes.D);
            break;
        case E:
            newTask = tasklist.addTask(this.input, TaskList.TaskTypes.E);
            break;
        }
        storage.save(tasklist);
        ui.showToUser(" Got it! I've added this task:",
                " - " + newTask,
                " Now you have " + tasklist.getListSize() + " task(s) in the list.\n");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
