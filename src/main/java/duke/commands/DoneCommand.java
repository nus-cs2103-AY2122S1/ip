package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;

public class DoneCommand extends Command {
    private final int taskIndex;

    public DoneCommand(int index) {
        this.taskIndex = index;
    }

    public int getTaskIndex() {
        return taskIndex;
    }

    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException {
        if (taskIndex <= 0 || taskIndex > task.getNumTasks() || task.isEmptyTaskList()) {
            String errorMessage = String.format("   INDEX ERROR: Task number %d does not exist to be completed", taskIndex);
            throw new InvalidInputException(errorMessage);
        } else {
            String completedTaskInfo = task.completeTask(taskIndex);
            ui.showCompletedTask(completedTaskInfo);
        }
    }

    public boolean isExit() {
        return false;
    }
}
