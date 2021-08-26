package duke;

import duke.tasks.Task;

import java.util.Scanner;

/**
 * Deals with Interactions with the user.
 * Handles both input and displaying output.
 */
public class Ui {
    private boolean isExit;
    private static final String GREETING = "Hello! I'm Duck \n"
            + "*quack*  >(.)__\n"
            + "          (___/ \n"
            + "What can I do for you?\n";
    private static final String BYE = "Bye. Hope to see you again soon!\n"
            + "   __(.)>   *quack*\n"
            + "~~ \\___)\n";

    private Scanner scanner;

    /**
     * Constructor for Ui object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
        this.isExit = false;
    }

    /**
     * Reads each line of command that user inputs.
     * @return String containing command and task description.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints greeting to users upon running.
     */
    public void showGreeting() {
        System.out.println(GREETING);
    }

    /**
     * Prints farewell greeting after user uses 'bye' command.
     */
    public void showBye() {
        this.scanner.close();
        this.isExit = true;
        System.out.println(BYE);
    }

    /**
     * Prints error message from DukeExceptions for users to correct their input.
     * @param errorMessage message from errors caught.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints IOException error message when opening the duke.txt file upon start.
     * @param errorMessage message from IOException.
     */
    public void showLoadingError(String errorMessage) {
        System.out.println("OOPS!! An error has occurred: " + errorMessage);
    }

    public boolean getIsExit() {
        return this.isExit;
    }

    /**
     * Inform user that task has been added.
     * @param task task that was added.
     * @param numTasks number of tasks in the user's tasklist.
     */
    public void showAdded(Task task, int numTasks) {
        System.out.println("Got it. I've added this task:\n"
                + task.toString());
        System.out.printf("Now you have %d tasks in your list.\n",
                numTasks);
    }

    /**
     * Inform user that task has been deleted.
     * @param deletedTask task that was deleted.
     * @param numTask number of remaining tasks in the user's tasklist.
     */
    public void showDeleted(Task deletedTask, int numTask) {
        System.out.println("Noted. I've removed this task: \n"
                + deletedTask.toString());
        System.out.printf("Now you have %d tasks in your list.\n",
                numTask);
    }

    /**
     * Inform user that task has already been marked as done.
     * @param task task that was already done.
     */
    public void showTaskDone(Task task) {
        System.out.println("This task has already been completed!\n"
                + task.toString());
    }

    /**
     * Inform user that task was marked as done.
     * @param task task that was marked as done.
     */
    public void showMarkedDone(Task task) {
        System.out.println("Nice! I've marked this task as done: \n"
                + task.toString());
    }

    /**
     * Displays the list of tasks in the user's TaskList when 'list' command is used.
     * @param tasks user's TaskList.
     */
    public void showList(TaskList tasks) {
        if (tasks.taskList.isEmpty()) {
            System.out.println("There are no duke.tasks on your list. *quack*");
        } else if (tasks.getLength() == 1) {
            System.out.println("There is one task on your list:");
            System.out.println("1. " + tasks.getTask(0).toString());
            System.out.println("*quack*");
        } else {
            System.out.println("Here are the duke.tasks on your list:");
            int i = 1;
            for (Task task : tasks.taskList) {
                System.out.println(i + ". " + task.toString());
                i++;
            }
            System.out.println("*quack*");
        }
    }
}