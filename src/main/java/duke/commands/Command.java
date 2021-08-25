package duke.commands;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;


/**
 * Abstract Command class used represent command inputs for Duke.
 * Contains methods that
 * (i)    executes the Command
 * (ii)   print out the number of tasks in a given TaskList
 * (iii)  counts the number of blank spaces in a string of text.
 */
public abstract class Command {
    private final String keyword;

    Command(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Abstract execute() method in Command for specific commands to execute their responsibilities.
     *
     * @param des   the user input into the Duke chat-box.
     * @param tList the TaskList object used to keep track of all tasks.
     */
    public abstract void execute(String des, TaskList tList) throws DukeException;

    /**
     * The numberOfTasks() method informs the user of the total number of tasks.
     *
     * @param tList the TaskList object used to keep track of all tasks.
     */
    public void numberOfTasks(TaskList tList) {
        ArrayList<Task> tasks = tList.getTaskList();
        if (tasks.size() == 1) {
            System.out.println("You now have " + tasks.size() + " task in the list");
        } else {
            System.out.println("You now have " + tasks.size() + " tasks in the list");
        }
    }

    /**
     * The countSpaces method counts the number of blank spaces in a given String.
     *
     * @param des the user input into the Duke chat-box.
     * @return Integer type object that represents the number of blank spaces in
     * the user input.
     */
    public int countSpaces(String des) {
        int count = 0;
        for (int i = 0; i < des.length(); i++) {
            if (des.charAt(i) == ' ') {
                count++;
            }
        }
        return count;
    }
}
