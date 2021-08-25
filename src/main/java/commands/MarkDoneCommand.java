package commands;

import java.io.IOException;

import duke.*;
import tasks.*;
import exceptions.*;

/**
 * This command marks a task in the task list as done.
 *
 */
public class MarkDoneCommand implements Command {

    // The index of the task to be marked as done
    int index;

    public MarkDoneCommand(int index) {
        this.index = index;
    }

    public void execute(Ui ui, TaskList taskList, Storage storage) {
        try {
            Task t = taskList.getTask(this.index);
            taskList.getTask(this.index).markAsDone();
            ui.printTaskDone(t, this.index);
            storage.writeTasksToFile(taskList, storage.getTaskFile());
        } catch (IndexOutOfBoundsException e) {
            if (taskList.numberOfTasks() > 0) {
                System.out.println("Invalid index given, enter a number from 1 to " + taskList.numberOfTasks());
            } else if (taskList.numberOfTasks() == 0) {
                System.out.println("You cannot mark any task as done because you have no tasks!");
            }
        } catch (IOException e) {
            ui.printFileWriteFail(storage.getTaskFile());
        }
    }

    public boolean isQuit() {return false;}
}
