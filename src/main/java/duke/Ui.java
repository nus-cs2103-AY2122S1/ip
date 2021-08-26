package duke;

import duke.task.Task;

/**
 * Ui class that handles UI and Outputs to the user.
 */
public class Ui {

    /**
     * Prints a horizontal line that separates user inputs from Duke's outputs.
     */
    public static void printLine() {
        System.out.println("____________________________________________");
    }

    /**
     * Prints the welcome message when Duke is ran.
     */
    public void printWelcomeMessage() {
        printLine();
        System.out.println("Hello I'm duke\nWhat can I do for you?");
        printLine();
    }

    /**
     * Prints the goodbye message when the user exits the program.
     */
    public void printGoodbyeMessage() {
        printLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Prints a message that informs the user which task has been marked as done.
     *
     * @param task The task that the user has marked as done.
     */
    public static void printDoneMessage(Task task) {
        printLine();
        System.out.println("\tNice! I've marked this done:\n\t  " + task);
        printLine();
    }

    /**
     * Prints a message that informs the user which task has been deleted.
     *
     * @param task The task that the user deletes from the task list.
     * @param taskCount The number of tasks left in the task list.
     */
    public static void printDeleteMessage(Task task, int taskCount) {
        printLine();
        System.out.println("\tNoted. I've removed this task:\n\t  " + task);
        printTaskCount(taskCount);
        printLine();
    }

    /**
     * Prints a message that informs the user which task has been added.
     *
     * @param task The task that the user adds to the task list.
     * @param taskCount The number of tasks in the list after adding.
     */
    public static void printAddedMessage(Task task, int taskCount) {
        printLine();
        System.out.println("\tGot it. I've added this task:\n" + "\t  " + task);
        printTaskCount(taskCount);
        printLine();
    }

    /**
     * Prints a message that informs user how many tasks they have in the task list
     *
     * @param taskCount The number of tasks in the task list.
     */
    private static void printTaskCount(int taskCount) {
        System.out.println("\tNow you have " + taskCount + " tasks in the list.");
    }

}
