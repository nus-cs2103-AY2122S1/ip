package duke.commands;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;

public abstract class Command {
    private final String keyword;

    Command(String keyword) {
        this.keyword = keyword;
    }

    public abstract void execute(String des, TaskList tList) throws DukeException;

    /**
     * The numberOfTasks() method informs the user of the total number of tasks.
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
