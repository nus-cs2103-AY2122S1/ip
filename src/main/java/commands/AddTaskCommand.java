package commands;

import java.io.IOException;

import duke.*;
import tasks.*;
import exceptions.*;

/**
 * This command adds a task to the taskList, given a description and flag.
 * TODO: Will split further to account for to-do, deadline and event.
 */

public class AddTaskCommand implements Command {

    String desc;
    boolean isDone;

    public AddTaskCommand(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    public void execute(Ui ui, TaskList taskList, Storage storage) {
        Task newTask = new Task(this.desc, this.isDone);
        taskList.addTask(newTask);
        ui.printAddTask(newTask);
        try {
            storage.writeTasksToFile(taskList, storage.getTaskFile());
        } catch (IOException e) {
            ui.printFileWriteFail(storage.getTaskFile());
        }
    }

    public boolean isQuit() { return false; }
}
