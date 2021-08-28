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
     * execute() method in ListCommand to list all tasks in the TaskList.
     *
     * @param des   the user input into the Duke chat-box.
     * @param tList the TaskList object used to keep track of all tasks.
     * @return String object to describe execution of ListCommand.
     */
    @Override
    public String execute(String des, TaskList tList) {
        String result = "Here are the tasks in your list:\n";
        ArrayList<Task> tasks = tList.getTaskList();
        int count = 1;
        for (Task t : tasks) {
            result = result + count + ". " + t.formatString() + "\n";
            count++;
        }
        return result;
    }
}
