package commands;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import tasks.Task;

/**
 * This command marks a task in the task list as done.
 *
 */
public class MarkDoneCommand implements Command {

    // The index of the task to be marked as done
    private final int index;

    public MarkDoneCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        try {
            Task t = taskList.getTask(this.index);
            taskList.getTask(this.index).markAsDone();
            storage.writeTasksToFile(taskList, storage.getTaskFile());
            return ui.getTaskDoneResponse(t, this.index);
        } catch (IndexOutOfBoundsException e) {
            if (taskList.numberOfTasks() > 0) {
                return "Invalid index given, enter a number from 1 to " + taskList.numberOfTasks();
            } else if (taskList.numberOfTasks() == 0) {
                return "You cannot mark any task as done because you have no tasks!";
            }
        } catch (IOException e) {
            return ui.getFileWriteFailResponse(storage.getTaskFile());
        }
        return "";
    }

    @Override
    public boolean isQuit() {
        return false;
    }
}
