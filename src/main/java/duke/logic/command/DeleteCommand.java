package duke.logic.command;

import duke.logic.tasks.TaskList;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String executeCommand(TaskList taskList) {
        return taskList.deleteTask(taskIndex);
    }
}
