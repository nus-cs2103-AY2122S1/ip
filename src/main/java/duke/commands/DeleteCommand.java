package main.java.duke.commands;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import main.java.duke.DukeException;
import main.java.duke.MainWindow;
import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.tasks.Task;

/**
 * A command that deletes a task from a task list.
 */
public class DeleteCommand extends Command {
    private final int taskNum;

    /**
     * Constructs a new delete task command with the given index.
     *
     * @param taskNum the given task index
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the delete command.
     *
     * @param tasks given list of tasks
     * @param gui given gui object
     * @param storage given storage object
     */
    public String execute(TaskList tasks, MainWindow gui, Storage storage) throws IOException, DukeException {
        if (tasks.getTaskList().size() < this.taskNum) {
            throw new DukeException("You cannot complete a task that does not exist!");
        } else {
            Scanner newSc = new Scanner(new File(storage.getFilePath()));
            storage.deleteTaskFromFile(taskNum, newSc, tasks);
            ArrayList<Task> taskList = tasks.getTaskList();
            Task taskToDelete = taskList.get(taskNum - 1);
            taskList.remove(taskToDelete);
            assert !(tasks.getTaskList().contains(taskToDelete)) : "Task list should not contain current task";
            String message1 = ("Noted. I've removed this task: \n");
            String message2 = taskToDelete.toString();
            String taskForm;
            if (taskList.size() == 1 || taskList.size() == 0) {
                taskForm = " task";
            } else {
                taskForm = " tasks";
            }
            String message3 = ("Now you have " + taskList.size() + taskForm + " in the list.");
            return message1 + message2 + message3;
        }
    }

}
