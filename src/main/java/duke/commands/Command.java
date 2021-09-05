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
     * Returns String representing specific commands executing their responsibilities.
     *
     * @param des   User input into the Duke chat-box.
     * @param tList TaskList object used to keep track of all tasks.
     * @return String representation of Command.
     * @throws DukeException on task-specific errors.
     */
    public abstract String execute(String des, TaskList tList) throws DukeException;

    /**
     * Returns string representing total number of tasks.
     *
     * @param tList TaskList object used to keep track of all tasks.
     * @return Total number of tasks.
     */
    public String numberOfTasks(TaskList tList) {
        ArrayList<Task> tasks = tList.getTaskList();
        if (tasks.size() == 1) {
            return "You now have " + tasks.size() + " task in the list";
        } else {
            return "You now have " + tasks.size() + " tasks in the list";
        }
    }

    /**
     * Returns Integer type object that represents the number of blank spaces in the user input.
     *
     * @param des User input into the Duke chat-box.
     * @return Number of blank spaces.
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

    /**
     * Checks for valid description using Java assert.
     *
     * @param des the user input into the Duke chat-box.
     */
    public void checkValidDes(String des) {
        assert des != "";
        assert des != null;
    }
}
