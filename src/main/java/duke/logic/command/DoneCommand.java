package duke.logic.command;

import duke.logic.tasks.TaskList;

public class DoneCommand extends Command {
    private int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String executeCommand(TaskList taskList) {
        return taskList.markTaskAsDone(taskIndex);
    }
}
