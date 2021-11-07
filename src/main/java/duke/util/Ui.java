package duke.util;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * This is an Ui class that deals with interaction with Users.
 */
public class Ui {

    /**
     * Class field of a Ui instance.
     */
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns the String for the standard welcome message user sees when initiating duke.
     *
     * @return A String of message to be shown in GUI.
     */
    public String showWelcomeMessage() {
        String welcomeMessage = "Hello I'm FullOfBugs. What can I do for you?";
        return welcomeMessage;
    }

    /**
     * Returns the String for the message when a task is added to the list.
     *
     * @param task  A Task instance that contains details of the newly added task and to be printed.
     * @param newSize An int representing the new size of the Task list.
     * @return A String of message to be shown in GUI.
     */
    public String showAddTaskMessage(Task task, int newSize) {
        String addTaskMessage = "Got it. I've added this task:\n"
                + String.format("%s\n", task)
                + String.format("Now you have %d ", newSize)
                + (newSize <= 1 ? "task" : "tasks") + " in the list.";
        return addTaskMessage;
    }

    /**
     * Returns the String for the message when a task is removed from the list.
     *
     * @param task  A Task instance that contains the details of the removed task.
     * @param remainingSize An int representing the newly updated size of task list.
     * @return A String of message to be shown in GUI.
     */
    public String showRemoveTaskMessage(Task task, int remainingSize) {
        String removeTaskMessage = "Noted. I've removed this task:\n"
                + String.format("%s\nNow you have %d ", task, remainingSize)
                + (remainingSize <= 1 ? "task" : "tasks")
                + " in the list.";
        return removeTaskMessage;
    }

    /**
     * Returns the String for the message when a task is marked as done.
     *
     * @param task A Task instance that contains details of the task marked as done.
     * @return A String of message to be shown in GUI.
     */
    public String showMarkTaskAsDoneMessage(Task task) {
        String markTaskAsDoneMessage = "Nice! I've marked this task as done:\n" + String.format("%s\n", task);
        return markTaskAsDoneMessage;
    }

    /**
     * Returns the String for the list of all the task currently in the list.
     *
     * @param tasks A TaskList instance containing a list of task.
     * @return A String of message to be shown in GUI.
     */
    public String showList(ArrayList<Task> tasks) {
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
     * @return A String of message to be shown in GUI.
     */
    public String showTasksWithKeyword(ArrayList<Task> tasks) {
        String message = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            message += String.format("%d. %s\n", i + 1, tasks.get(i));
        }
        return message;
    }

    /**
     * Returns the farewell message when the user says bye to Duke when using CLI.
     */
    public String bidFarewell() {
        String farewellMessage = "Bye. Hope to see you again soon!";
        this.scanner.close();
        return farewellMessage;
    }

    /**
     * Returns a string representing the command keyed in by the user.
     *
     * @return A String representing a single command.
     */
    public String readCommand() {
        String command = this.scanner.nextLine();
        return command;
    }

}
