package duke.logic.command;

import duke.logic.tasks.TaskList;
import duke.logic.tasks.ToDo;

public class ToDoCommand extends Command {
    private ToDo task;

    public ToDoCommand(ToDo task) {
        this.task = task;
    }

    @Override
    public String executeCommand(TaskList taskList) {
        return taskList.addTask(task);
    }
}
