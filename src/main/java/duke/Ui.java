package duke;

import java.util.ArrayList;

/**
 * Class includes methods required for creating the user interface.
 */
public class Ui {

    /**
     * Method provides message for starting the application.
     */
    public void start() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________"
                + "________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("_____________________________"
                + "_______________________________");
    }

    /**
     * Method provides message for ending the application.
     */
    public void end() {
        System.out.println("    ________________________________"
                + "____________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ______________________________"
                + "______________________________");
    }

    /**
     * Method provides message for adding a task.
     */
    public void addTaskMessage() {
        System.out.println("     Got it. I've added this task:");
    }

    /**
     * Method provides message for returning number of tasks.
     *
     * @param count represents number of tasks
     */
    public void printNumberOfTasks(int count) {
        System.out.println("     Now you have " + count
                + " task" + ((count > 1) ? "s" : "") + " in the list.");

    }

    /**
     * Method provides message for returning current task.
     *
     * @param currTask current task
     */
    public void printCurrentTask(Task currTask) {
        System.out.println("       " + currTask.toString());
    }

    /**
     * Method provides message for listing tasks.
     */
    public void listTaskMessage() {
        System.out.println("     Here are the tasks in your list:");
    }

    /**
     * Method provides message for listing each task.
     *
     * @param xs list of tasks
     * @param a index of task
     */
    public void listEachTask(ArrayList<Task> xs, int a) {
        System.out.println("     " + (a + 1) + ". " + xs.get(a).toString());
    }

    /**
     * Method provides message for invalid input.
     */
    public void printInvalidInput() {
        System.out.println("    Invalid Input. Please try again.");
    }

    /**
     * Method provides message for invalid task number.
     */
    public void printInvalidTaskNumber() {
        System.out.println("     Invalid task number. Please try again.");
    }

    /**
     * Method provides message for completing task.
     */
    public void printDoneMessage() {
        System.out.println("     Nice! I've marked this task as done!");
    }

    /**
     * Method provides message for deleting task.
     */
    public void printDeletedMessage() {
        System.out.println("     Noted. I've removed this task:");
    }

    /**
     * Method provides message for list of similar tasks.
     *
     * @param similarTasks represents arraylist with common tasks
     */
    public void printSimilarTasks(ArrayList<String> similarTasks) {
        for (int i = 0; i < similarTasks.size(); i++) {
            System.out.println(similarTasks.get(i));
        }
    }

}
