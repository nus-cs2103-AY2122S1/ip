package duke.commands;

import java.util.ArrayList;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

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
            if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                System.out.println(d.formatString());
            } else if (t instanceof Event) {
                Event e = (Event) t;
                System.out.println(e.formatString());
            } else if (t instanceof ToDo) {
                ToDo td = (ToDo) t;
                System.out.println(td);
            } else {
                System.out.println(t.toString());
            }
            count++;
        }
    }
}
