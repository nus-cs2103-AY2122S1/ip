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
     */
    @Override
    public void execute(String des, TaskList tList) {
        ArrayList<Task> tasks = tList.getTaskList();
        int count = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task t : tasks) {
            System.out.print(count + ". ");
            System.out.println(t.formatString());
            count++;
        }
    }
}
