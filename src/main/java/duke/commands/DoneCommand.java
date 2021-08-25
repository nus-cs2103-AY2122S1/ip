package duke.commands;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasks.Task;
import duke.TaskList;

public class DoneCommand extends Command {
    private int taskNum;

    /**
     * Constructs a new mark task as done command with the given index.
     * 
     * @param taskNum the given task index
     */
    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the mark as done command.
     * 
     * @param tasks given list of tasks
     * @param ui given ui object
     * @param storage given storage object
     * @throws IOException
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        ArrayList<Task> taskList = tasks.getTaskList();
        if (taskList.size() < this.taskNum) {
            throw new DukeException("You cannot complete a task that does not exist!");
        } else {
            Scanner newSc = new Scanner(new File(storage.getFilePath()));
            storage.markAsDoneInFile(taskNum, newSc, tasks);
            completeTask(taskList.get(taskNum - 1));
        }
    };

    public boolean isExit() {
        return false;
    }

    /**
     * Mark a task's statis as done and print out the result.
     * 
     * @param task the task to be marked as done
     */
    public void completeTask(Task task) {
        System.out.println("Nice! I've marked this task as done: ");
        task.markAsDone();
        task.showThisTask();
    }
}
