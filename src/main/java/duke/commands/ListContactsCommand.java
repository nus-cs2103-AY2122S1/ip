package main.java.duke.commands;
import java.util.ArrayList;

import main.java.duke.MainWindow;
import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.tasks.Task;

/**
 * A command that lists all tasks from the task list.
 */
public class ListContactsCommand extends ContactCommand {

    /**
     * Constructs a new list tasks command with the given index.
     */
    public ListContactsCommand() {

    }

    /**
     * Executes the list command.
     *
     * @param contacts given list of tasks
     * @param gui given gui object
     * @param storage given storage object
     */
    public String execute(TaskList contacts, MainWindow gui, Storage storage) {
        ArrayList<Task> contactsList = contacts.getTaskList();
        StringBuilder message1 = new StringBuilder(("Here are the contacts in your list: \n"));
        for (int i = 0; i < contactsList.size(); i++) {
            Task t = contactsList.get(i);
            message1.append(i + 1).append(".").append(t.toString());
        }
        return message1.toString();
    }
}
