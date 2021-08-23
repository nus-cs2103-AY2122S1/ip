package jwbot.ui;

import jwbot.data.task.Task;
import jwbot.data.TaskList;

import java.util.Scanner;

/**
 * The class that is in charge of user interaction with the bot
 *
 * @author  Yim Jaewon
 */
public class Ui {

    private Scanner sc;

    private final static String greeting = "Wassup bro! I'm jwbot.JWBot\n"
            + "How can I help you?\n";

    private final static String byeMessage = "You leaving already? See you soon bro!";

    /**
     * The constructor of the ui class. Initializes a scanner.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Show welcome messages to the user.
     */
    public void showWelcome() {
        System.out.println(greeting);
    }

    /**
     * Show bye messages to the user.
     */
    public void showBye() {
        System.out.println(byeMessage);
    }

    /**
     * Read the command entered by the user.
     *
     * @return the command entered by the user as string
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Shows the loading error message to the user.
     */
    public void showLoadingError() {
        System.out.println("Oops bro, there's an error with loading saved data");
    }

    /**
     * Show the message that the delete attempt was successful to the user.
     *
     * @param task a task that is deleted
     * @param listSize the total size of the list after the deletion
     */
    public void showDeleteSuccessMessage(Task task, int listSize) {
        System.out.println("OK Bro, I noted you've deleted this task:\n" +
                task);
        System.out.println("So bro, now you have " + listSize + " tasks stored in the list!");
    }

    /**
     * Show the list of the tasks to the user.
     *
     * @param tasks The list of the tasks stored currently
     */
    public void showList(TaskList tasks) {
        System.out.println("OK bro, the tasks in your list are: ");
        for (int i = 1; i < tasks.getSize() + 1; i++) {
            System.out.println(i + ". " + tasks.getTask(i - 1));
        }
        System.out.println("Bro, now you have " + tasks.getSize() + " task(s) stored in the list!");
    }

    /**
     * Show the message that the task is marked done successfully to the user.
     *
     * @param doneTask The task that the user marked done
     */
    public void showDoneSuccessMessage(Task doneTask) {
        System.out.println("OK Bro, I noted you've done this task:\n" +
                doneTask);
    }

    /**
     * Show the message that the task was added successfully to the user.
     *
     * @param task The list of the tasks stored currently
     */
    public void showAddTaskSuccessMessage(Task task) {
        System.out.println("OK bro, I just added: " + task);
    }

    /**
     * Show the error message to the user.
     *
     * @param error The error message description
     */
    public void showError(String error) {
        System.out.println(error);
    }

}
