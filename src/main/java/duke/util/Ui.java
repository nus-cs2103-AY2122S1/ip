package duke.util;

import java.util.ArrayList;

import duke.task.Task;

/**
 * This is an Ui class that deals with interaction with Users.
 */
public class Ui {

    /**
     * Returns the String for the standard welcome message user sees when initiating duke.
     *
     * @return A String of message printed out in CLI.
     */
    public String showWelcomeMessage() {
        String message = "Hello I'm FullOfBugs. What can I do for you?";
        System.out.println(message);
        return message;
    }

    /**
     * Returns the String for the error message from DukeExceptions.
     *
     * @param message A String representing the message to be printed.
     * @return A String of message printed out in CLI.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Returns the String for the message when a task is added to the list.
     *
     * @param task  A Task instance that contains details of the newly added task and to be printed.
     * @param newSize An int representing the new size of the Task list.
     * @return A String of message printed out in CLI.
     */
    public String printAddTask(Task task, int newSize) {
        String message = "Got it. I've added this task:\n" + String.format("%s\n", task)
                + String.format("Now you have %d ", newSize) + (newSize <= 1 ? "task" : "tasks") + " in the list.";
        return message;
    }

    /**
     * Returns the String for the message when a task is removed from the list.
     *
     * @param task  A Task instance that contains the details of the removed task.
     * @param remainingSize An int representing the newly updated size of task list.
     * @return A String of message printed out in CLI.
     */
    public String printRemoveTask(Task task, int remainingSize) {
        String message = "Noted. I've removed this task:\n"
                + String.format("%s\nNow you have %d ", task, remainingSize)
                + (remainingSize <= 1 ? "task" : "tasks") + " in the list.";
        return message;
    }

    /**
     * Returns the String for the message when a task is marked as done.
     *
     * @param task A Task instance that contains details of the task marked as done.
     * @return A String of message printed out in CLI.
     */
    public String printMarkTaskDone(Task task) {
        String message = "Nice! I've marked this task as done:\n" + String.format("%s\n", task);
        return message;
    }

    /**
     * Returns the String for the list of all the task currently in the list.
     *
     * @param tasks A TaskList instance containing a list of task.
     * @return A String of message printed out in CLI.
     */
    public String printList(ArrayList<Task> tasks) {
        String message = "Your current tasks are:\n";
        for (int i = 0; i < tasks.size(); i++) {
            message += String.format("%d. %s\n", i + 1, tasks.get(i));
        }
        return message;
    }


    /**
     * Returns the String for the list of task that contains a specified keyword.
     *
     * @param tasks  An ArrayList of task that contains the keyword in its description.
     * @return A String of message printed out in CLI.
     */
    public String printFindTask(ArrayList<Task> tasks) {
        String message = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            message += String.format("%d. %s\n", i + 1, tasks.get(i));
        }
        return message;
    }

    /**
     * Returns the String for the farewell message when the user says bye to Duke.
     *
     * @return A String of message printed out in CLI.
     */
    public String bidFarewell() {
        String message = "Bye. Hope to see you again soon!";
        return message;
    }

}
