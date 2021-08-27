package main.java.duke.commands;
import java.io.IOException;
import java.util.ArrayList;

import main.java.duke.DukeException;
import main.java.duke.Storage;
import main.java.duke.Ui;
import main.java.duke.tasks.Task;
import main.java.duke.TaskList;

public class ListCommand extends Command {

    /**
     * Constructs a new list tasks command with the given index.
     */
    public ListCommand() {

    }

    /**
     * Executes the list command.
     * 
     * @param tasks given list of tasks
     * @param ui given ui object
     * @param storage given storage object
     * @throws IOException
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskList = tasks.getTaskList();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            System.out.print((i+1) + ".");
            t.showThisTask();
        }
    };

    public boolean isExit() {
        return false;
    }
}
