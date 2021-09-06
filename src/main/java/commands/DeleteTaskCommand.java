package commands;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import tasks.Task;


public class DeleteTaskCommand implements Command {

    private final int index;

    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        try {
            Task task = taskList.getTask(this.index);
            taskList.deleteTask(this.index);
            storage.writeTasksToFile(taskList, storage.getTaskFile());
            return ui.getDeleteTaskResponse(task, this.index);
        } catch (IndexOutOfBoundsException e) {
            if (taskList.numberOfTasks() > 0) {
                return "Invalid index given, enter a number from 1 to " + taskList.numberOfTasks();
            } else if (taskList.numberOfTasks() == 0) {
                return "You cannot delete any task because you have no tasks!";
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
