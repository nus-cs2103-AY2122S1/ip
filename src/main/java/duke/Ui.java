package duke;

import java.util.ArrayList;

/**
 * Class includes methods required for creating the user interface.
 */
public class Ui {

    /**
     * Method provides message for starting the application.
     */
    public String start() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * Method provides message for ending the application.
     */
    public static String end() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Method provides message for adding a task.
     */
    public String addTaskMessage() {
        return "Got it. I've added this task:";
    }

    /**
     * Method provides message for returning number of tasks.
     *
     * @param count represents number of tasks
     */
    public String printNumberOfTasks(int count) {
        return "     Now you have " + count
                + " task" + ((count > 1) ? "s" : "") + " in the list.";
    }

    /**
     * Method provides message for returning current task.
     *
     * @param currTask current task
     */
    public String printCurrentTask(Task currTask) {
        return "       " + currTask.toString();
    }

    /**
     * Method provides message for listing tasks.
     */
    public String listTaskMessage() {
        return "Here are the tasks in your list:";
    }

    /**
     * Method provides message for listing all tasks.
     *
     * @param xs list of tasks
     * @param count number of tasks
     */
    public String listAllTasks(ArrayList<Task> xs, int count) {
        String tasksAsString = "";
        int a = 0;
        while (a < count) {
            tasksAsString = tasksAsString + (a + 1) + ". " + xs.get(a).toString() + "\n";
            a = a + 1;
        }
        return tasksAsString;
    }

    /**
     * Method provides message for invalid input.
     */
    public String printInvalidInput() {
        return "Invalid Input. Please try again.";
    }

    /**
     * Method provides message for invalid task number.
     */
    public String printInvalidTaskNumber() {
        return "Invalid task number. Please try again.";
    }

    /**
     * Method provides message for completing task.
     */
    public String printDoneMessage() {
        return "Nice! I've marked this task as done!";
    }

    /**
     * Method provides message for deleting task.
     */
    public String printDeletedMessage() {
        return "Noted. I've removed this task:";
    }

    /**
     * Method provides message for list of similar tasks.
     *
     * @param similarTasks represents arraylist with common tasks
     */
    public String printSimilarTasks(ArrayList<String> similarTasks) {
        String similarTasksAsString = "";
        for (int i = 0; i < similarTasks.size(); i++) {
            similarTasksAsString = similarTasksAsString + similarTasks.get(i) + "\n";
        }
        return  similarTasksAsString;
    }

}
