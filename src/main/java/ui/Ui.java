package ui;

import task.Task;
import task.TaskList;

public class Ui {
    /**
     * Returns a farewell message to the terminal.
     */
    public static String goodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns the list of tasks to the terminal.
     */
    public static String listMessage(TaskList tasks) {
        if (tasks.size() == 0) {
            return "There are currently no tasks.";
        } else {
            String result = "";
            for (int i = 0; i < tasks.size(); i++) {
                result += (i + 1) + "." + tasks.getTask(i).toString() + "\n";
            }
            return result;
        }
    }

    /**
     * Returns an indication that a Task is marked as done to the terminal.
     */
    public static String doneMessage(Task task) {
        return "Nice! I've marked this task as done: \n" + "  " + task.toString();
    }

    /**
     * Returns a error message that there is no such task to the terminal.
     */
    public static String noSuchTaskMessage() {
        return "There is no such task.";
    }

    /**
     * Returns an indication that a Task is removed to the terminal.
     */
    public static String removeMessage(Task task, int num) {
        return "Noted. I've removed this task: \n" + "  " + task.toString() + "\n"
                + "Now you have " + num + " tasks in the list.";
    }

    /**
     * Returns a loading error message to the terminal.
     */
    public static String showLoadingError() {
        return "There was an error loading the data file.";
    }
}
