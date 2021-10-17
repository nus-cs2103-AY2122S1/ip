package duke;

import java.util.Scanner;

import duke.task.Task;

/**
 * Handles all User Interface of the program.
 */
public class Ui {

    private String logo = "Duke the frog~";

    public Ui() {

    }

    /**
     * Display the welcome message to the user.
     */
    public String getWelcome() {
        return "Hello from\n" + logo + "\n" + "What can I do for you?";
    }

    /**
     * Display a prompt asking user for an action.
     */
    public String showLoopWelcome() {
        return "What else can I do for you?";
    }

    /**
     * Reads input by user and returns what user have typed.
     *
     * @return A String written by user.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        try {
            input = scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return input.trim();
    }

    /**
     * Prints out an error message.
     *
     * @param msg The error message that is to be printed.
     */
    public String printErrorMessage(String msg) {
        return msg;
    }

    /**
     * Display loading error message.
     */
    public void showLoadingError() {
        System.out.println("Initialisation Error! We will override with a fresh state.");
    }

    /**
     * Display goodbye message.
     */
    public String goodBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Display task removed message.
     *
     * @param taskToBeDeleted The Task that is to be deleted.
     * @param totalTask The remaining number of task.
     * @return String msg to be printed
     */
    public String taskRemovedMessage(Task taskToBeDeleted, int totalTask) {
        String msg = "Noted. I've removed this task:\n";
        msg += "\t" + taskToBeDeleted.toString() + "\n";
        msg += "Now you have " + totalTask + " task in the list.";
        return msg;
    }

    /**
     * Display task added message.
     *
     * @param task The Task that is to be Added.
     * @param totalTask The new total number of task.
     * @return String msg to be printed
     */
    public String taskAddedMessage(Task task, int totalTask) {
        if (task != null) {
            String msg = "Got it. I've added this task." + "\n";
            msg += task + "\n";
            msg += "Now you have " + totalTask + " tasks in the list.";
            return msg;
        } else {
            return "Please check your fields once again!";
        }

    }

    /**
     * Display deadline added message.
     *
     * @param task The Task that is to be Added.
     * @param totalTask The new total number of task.
     * @return String msg to be printed
     */
    public String taskWithDateAddedMessage(Task task, int totalTask) {
        if (task != null) {
            String msg = "Got it. I've added this task." + "\n";
            msg += task + "\n";
            msg += "Now you have " + totalTask + " tasks in the list.";
            return msg;
        } else {
            return "Incorrect date format! Please follow YYYY-MM-DD for the date";
        }

    }
}
