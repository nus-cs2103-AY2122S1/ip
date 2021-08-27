package Duke;

import java.util.ArrayList;

/**
 * This class encapsulated the management of the user interface.
 */
public class Ui {

    final static String lineBreak = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    /**
     * Introduction method shown when program is first run.
     */
    public void introduceYourself() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm\n" + logo + "What can I do for you?");
        System.out.println(lineBreak);
    }

    /**
     * Goodbye message when the user enters the "bye" command.
     */
    public void printByeMessage() {
        System.out.println(lineBreak);
        System.out.println("Duke says: Bye. Hope to see you again soon!");
        System.out.println(lineBreak);
    }

    /**
     * Prints out the list of tasks.
     *
     * @param tasks The current list of tasks.
     */
    public void printTasks(ArrayList<Task> tasks) {
        System.out.println(lineBreak);
        System.out.println("Duke says: Here is your list of tasks :)");
        if (tasks.size() == 0) {
            System.out.println("Looks like you don't have any pending tasks! Must be nice (-_-;)");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + "."
                        + tasks.get(i).toString());
            }
        }
        System.out.println(lineBreak);
    }

    /**
     * Prints confirmation that a task was added successfully.
     *
     * @param task The task that was added.
     * @param numTasks The new total number of tasks.
     */
    public void printTaskAddition(Task task, int numTasks) {
        System.out.println(lineBreak);
        System.out.println("Duke says: I've added the task: ");
        System.out.println("     " + task.toString());
        System.out.println("You now have " + numTasks + " tasks, jiayouz!");
        System.out.println(lineBreak);
    }

    /**
     * Prints confirmation that a task was successfully deleted.
     *
     * @param task The task that was deleted.
     * @param numTasks The new total number of tasks.
     */
    public void printTaskDeletion(Task task, int numTasks) {
        System.out.println(lineBreak);
        System.out.println("Duke says: I've deleted the task: ");
        System.out.println("     " + task.toString());
        System.out.println("You now have " + numTasks + " tasks, jiayouz!");
        System.out.println(lineBreak);
    }

    /**
     * Prints confirmation that a task was successfully completed.
     *
     * @param task The task that was completed.
     * @param numTasks The current total number of tasks.
     */
    public void printTaskCompletion(Task task, int numTasks) {
        System.out.println(lineBreak);
        System.out.println("Duke says: You've completed the task: ");
        System.out.println("     " + task.toString() + "Well done!");
        System.out.println("You now have " + numTasks + " tasks, jiayouz!");
        System.out.println(lineBreak);
    }

    /**
     * Prints an error message to the user.
     *
     * @param msg The error that was thrown.
     */
    public void printErrorMessage(Exception msg) {
        System.out.println(lineBreak);
        System.out.println(msg.getMessage());
        System.out.println(lineBreak);
    }

    /**
     * Prints a message to tell the user that Duke does not undertand their input.
     */
    public void printUnknownCommandMessage() {
        System.out.println(lineBreak);
        System.out.println("Duke says: Sorry I don't understand what that means");
        System.out.println(lineBreak);
    }

    /**
     * Prints a message to tell the user that the task number they are trying to complete/delete does not exist.
     */
    public void printInvalidIndexMessage() {
        System.out.println(lineBreak);
        System.out.println("Duke says: You don't have that many tasks!");
        System.out.println(lineBreak);
    }
}
