package main.java.duke.commands;
import java.io.IOException;
import java.util.ArrayList;

import main.java.duke.*;
import main.java.duke.tasks.Task;

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
     * @param gui given gui object
     * @param storage given storage object
     * @throws IOException
     * @throws DukeException
     */
    public String execute(TaskList tasks, MainWindow gui, Storage storage) {
        ArrayList<Task> taskList = tasks.getTaskList();
        String message1 = ("Here are the tasks in your list: \n");
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            message1 += ((i + 1) + ".") + t.toString();
        }
        return message1;
    };

    public boolean isExit() {
        return false;
    }
}
