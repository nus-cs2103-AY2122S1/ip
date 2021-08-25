package duke.ui;

import duke.task.Task;

import java.util.Scanner;

public class Ui { //IU Class used to handle the interactions with the user.
    private final Scanner sc;

    /**
     * Constructor for the Ui class
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Calls the welcome Message for Duke
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
    }

    /**
     * Calls the goodbye message for Duke
     */
    public void showGoodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Calls the divider line
     */
    public void showLine() {
        System.out.println("_______________");
    }

    /**
     * Calls the message to inform the user that the task has been added successfully
     * @param taskList List of tasks
     */
    public void showAddedMessage(TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + (taskList.get(taskList.getCount() - 1)));
        System.out.println("Now you have " + taskList.getCount() + " in the list.");
    }

    /**
     * Reads the input by the user and returns it for the parser
     * @return the input by the user
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints the list of task for the users
     * @param taskList Takes in the current list of tasks
     */
    public static void printList(TaskList taskList) {
        taskList.printList();
    }

    /**
     * Prints the done Message to inform the user that the task has been marked as done
     * @param msg  task's message when marked as done
     */
    public static void doneMessage(String msg) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + msg);
    }

    /**
     * Prints the delete message to inform the user that the task has been deleted properly
     * @param task Task to be deleted
     * @param index The number of tasks that remains in the list
     */
    public static void deleteMessage(Task task, int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + index + " in the list.");
    }

    /**
     * Prints the error to inform the user that their file format is incorrect
     */
    public static void showLoadingError() {
        System.out.println("☹ OOPS!!! It seems like your file format is incorrect!");
    }
}
