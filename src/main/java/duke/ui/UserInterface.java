package duke.ui;

import duke.task.TaskList;

import java.util.ArrayList;

/**
 *  Contains the interface that mimics the interaction with users.
 *  Methods contained within this class prints directly onto the command line.
 */
public class UserInterface {

    public UserInterface() {}

    /**
     * Greets the user during the first execution of the program.
     */
    public void greet() {
        System.out.println("Duke: Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Signs off and exits the program on command execution, interpreted by
     * the Parser class.
     */
    public void exit() {
        System.out.println("\nDuke: Bye. Hope to see you again soon!");
        System.exit(0);
    }

    /**
     * Prints the header for the task list that follows right after this
     * method executes.
     */
    public void taskListHeader() {
        System.out.println("\nHere are the tasks in your list:\n--------------------");
    }

    public void matchingTaskListHeader(String keyword) {
        System.out.println("\nHere are the tasks matching \"" + keyword + "\" in your list:\n--------------------");
    }

    /**
     * Prints the given array list onto the command line.
     *
     * @param taskListArrayList the input array list to be printed
     */
    public void printArrayList(ArrayList<TaskList> taskListArrayList) {
        int index = 1;
        for (TaskList t : taskListArrayList) {
            System.out.println(index + ". " + t);
            index++;
        }
    }

    /**
     * Prints a confirmation that the input task has been marked as done.
     *
     * @param t the input task that has just been marked as done
     */
    public void taskComplete(TaskList t) {
        System.out.println("\nDuke: Nice! I've marked this task as done:\n" + t);
    }

    /**
     * Prints a confirmation that the input task has been deleted from the task list. Thereafter,
     * it prints the length of the task list after deletion.
     *
     * @param t the input task that has just been deleted from task list
     */
    public void taskDeleted(TaskList t) {
        System.out.println("\nDuke: Noted. I've removed this task:\n"
            + t + "\nNow you have " + TaskList.listLength() + " tasks in the list.");
    }

    /**
     * Prints a confirmation that the input task has been added to the task list. Thereafter,
     * it prints the length of the task list after addition.
     *
     * @param t the input task that has just been added to the task list
     */
    public void taskAdded(TaskList t) {
        System.out.println("\nDuke: Got it. I have added this task:\n"
                + t + "\nNow you have "
                + TaskList.listLength()
                + " tasks in the list.");
    }

    /**
     * Prints a notification warning that the task list is currently empty.
     */
    public void emptyListWarning() {
        System.out.println("Your list is empty!");
    }

    /**
     * Prints a notification warning that warns the user that their input is not a numerical
     * value.
     */
    public void integerInputWarning() {
        System.out.println("Please enter a number as index!");
    }

    /**
     * Prints a notification warning that warns the user that their input is not within the
     * specified range of allowed indexes.
     */
    public void invalidIntegerWarning() {
        System.out.println("Please enter a proper index!");
    }

    /**
     * Prints a notification warning that warns the user that the task file is not found. Also
     * notifies user that they should create a data folder in the displayed root in order to allow
     * for the automatic creation of a tasks.txt file.
     */
    public void fileNotFoundWarning() {
        System.out.println("File not found. Please add an item to automatically create your tasks.txt!"
                + " If no file is created,\nyou need to create a data folder in your root directory before "
                + "proceeding. Your root is " + System.getProperty("user.dir"));
    }

    /**
     * Prints a notification warning that warns the user about a malfunction. The input message is the error
     * message to be displayed to the user.
     *
     * @param message the error message that is to be displayed to the user
     */
    public void generalErrorWarning(String message) {
        System.out.println("Something went wrong: " + message);
    }
    /**
     * A dud function that does nothing. Used to silence warnings from empty catch blocks.
     */
    public void nullFunction() {}
}
