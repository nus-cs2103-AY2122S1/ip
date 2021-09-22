package duke;

import java.util.ArrayList;

/**
 * Class includes methods required for creating the user interface.
 */
public class Ui {

    /**
     * Returns message for starting the application.
     *
     * @return starting message in string format
     */
    public String start() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * Returns message for ending the application.
     *
     * @return ending message in string format
     */
    public static String end() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns message for adding a task.
     *
     * @return introduction message for adding a task in string format
     */
    public String addTaskMessage() {
        return "Got it. I've added this task:";
    }

    /**
     * Returns message for returning number of tasks.
     *
     * @param count represents number of tasks
     * @return number of tasks in string format
     */
    public String produceNumberOfTasks(int count) {
        return "     Now you have " + count
                + " task" + ((count > 1) ? "s" : "") + " in the list.";
    }

    /**
     * Returns message for returning current task.
     *
     * @param currTask current task
     * @return specific task in string format
     */
    public String produceCurrentTask(Task currTask) {
        return "       " + currTask.toString();
    }

    /**
     * Returns message for listing tasks.
     *
     * @return introduction message for listing tasks in string format
     */
    public String listTaskMessage() {
        return "Here are the tasks in your list:";
    }

    /**
     * Returns message for listing all tasks.
     *
     * @param xs list of tasks
     * @param count number of tasks
     * @return list of tasks in string format
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
     * Returns message for invalid input.
     *
     * @return invalid input message
     */
    public String produceInvalidInput() {
        return "Invalid Input. Please try again.";
    }

    /**
     * Returns message for invalid task number.
     *
     * @return invalid task number message
     */
    public String produceInvalidTaskNumber() {
        return "Invalid task number. Please try again.";
    }

    /**
     * Returns message for completing task.
     *
     * @return introduction message for completing a task
     */
    public String produceDoneMessage() {
        return "Nice! I've marked this task as done!";
    }

    /**
     * Returns message for deleting task.
     *
     * @return introduction message for deleting a task
     */
    public String produceDeletedMessage() {
        return "Noted. I've removed this task:";
    }

    /**
     * Returns message for listing similar tasks.
     *
     * @param similarTasks represents arraylist with common tasks
     * @return list of similar tasks in string format
     */
    public String produceSimilarTasks(ArrayList<String> similarTasks) {
        String similarTasksAsString = "";
        for (int i = 0; i < similarTasks.size(); i++) {
            similarTasksAsString = similarTasksAsString + similarTasks.get(i) + "\n";
        }
        return  similarTasksAsString;
    }

}
