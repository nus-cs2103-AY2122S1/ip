package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;
/**
 * Contains the logic to display the UI for the user to see.
 *
 * @author Benjamin Lui
 */

public class Ui {
    private final Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Displays the welcome text for duke.Duke.
     * @param taskList to output the list of tasks duke.Duke currently has at start
     */
    public String showWelcome(TaskList taskList) {
        String greeting = "Hello! I'm Duke\n" + "What can I do for you?\n";
        String currTasks = "These are the current tasks I have:\n";
        String lst = listView(taskList.getAllTasks());
        return greeting + currTasks + lst;

    }

    /**
     * Reads a command from the user.
     * @return a string command of the user input
     */
    public String readCommand() {
        String inputCommand = in.nextLine();
        return inputCommand;
    }

    /**
     * Displays the bye text for duke.Duke.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays an error text based on the error message.
     * @param errorMsg error message to be displayed
     */
    public String showError(String errorMsg) {
        return errorMsg;
    }

    /**
     * Displays a text when the user adds a task.
     */
    public String addMessage() {
        return "Got it. I've added this task:\n";
    }

    /**
     * Displays the task's toString() method with a new line at the end.
     * @param task task to be displayed
     */
    public String showTask(Task task) {
        return task.toString() + "\n";
    }

    /**
     * Displays the number of tasks that duke.Duke currently has.
     * @param lst the list of tasks needed to output the number of items in it
     */
    public String showListLength(TaskList lst) {
        return "Now you have " + lst.size() + " tasks in the list\n";
    }

    /**
     * Displays a message when a task has been removed.
     */
    public String deletedMsg() {
        return "Noted. I've removed this task:\n";
    }

    /**
     * Displays a list view of the tasks that duke.Duke has with a header
     * @param lst the list of tasks to be displayed
     */
    public String listView(ArrayList<Task> lst) {
        return "Here are the tasks in your list:\n" + list(lst);
    }

    /**
     * Displays only the list view of tasks that duke.Duke has.
     * @param lst the list of tasks to be displayed
     * @return the list view of the tasks in the list of tasks
     */

    public String list(ArrayList<Task> lst) {
        String listOfTasks = "";
        int counter = 1;
        for (Task tsk : lst) {
            listOfTasks += counter + ". " + tsk.toString() + System.lineSeparator();
            counter++;
        }
        return listOfTasks;
    }

    /**
     * Displays a message when a task is marked done.
     * @param tsk the task that was done
     */
    public String doneTask(Task tsk) {
        String initialMsg = "Nice! I've marked this task as done:\n";
        return initialMsg + tsk.toString();
    }

    /**
     * Displays a message if the file had error loading.
     */
    public void showLoadingError() {
        System.out.println("error loading!");
    }

    /**
     * Displays a message for the matching tasks.
     */
    public String matchingTasks() {
        return "Here are the matching tasks in your list:\n";
    }
}
