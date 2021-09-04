package duke.commands;

import java.util.ArrayList;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * ListCommand class used to represent a List Command.
 * Contains methods that
 * (i)    executes the ListCommand
 */
public class ListCommand extends Command {
    public ListCommand() {
        super("list");
    }

    /**
     * Returns String object to list all tasks in TaskList.
     *
     * @param des   the user input into the Duke chat-box.
     * @param tList the TaskList object used to keep track of all tasks.
     * @return String representation of ListCommand.
     */
    @Override
    public String execute(String des, TaskList tList) {
        checkValidDes(des);
        String result = "Here are the tasks in your list:\n\n";
        ArrayList<Task> tasks = tList.getTaskList();
        int count = 1;
        for (Task t : tasks) {
            result = result + count + ". " + t.formatString() + "\n\n";
            count++;
        }
        return result + "\n";
    }
}
