package commands;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import tasks.Task;

/**
 * This command adds a task to the taskList, given a description and flag.
 * TODO: Will split further to account for to-do, deadline and event.
 */

public class AddTaskCommand implements Command {

    private Task taskToAdd;

    public AddTaskCommand(String desc, boolean isDone) {
        this.taskToAdd = new Task(desc, isDone);
    }

    public AddTaskCommand(Task task) {
        this.taskToAdd = task;
    }

    public String execute(Ui ui, TaskList taskList, Storage storage) {
        try {
            taskList.addTask(taskToAdd);
            storage.writeTasksToFile(taskList, storage.getTaskFile());
            return ui.getAddTaskResponse(taskToAdd);
        } catch (IOException e) {
            return ui.getFileWriteFailResponse(storage.getTaskFile());
        }
    }

    public boolean isQuit() {
        return false;
    }
}
