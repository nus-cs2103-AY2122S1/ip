package duke;
import java.util.Scanner;

/**
 * Class that handles all user interaction.
 * Responsible for outputs as well as reading inputs from users.
 */
public class Ui {
    private static final Scanner USER_INPUT = new Scanner(System.in);
    private static final String GREETING = "Hello! I'm Duke\n" + "What can I do for you?";
    private static final String FAREWELL = "Bye. Hope to see you again soon!";
    private static final String LIST_MESSAGE = "Here are the tasks in your list:";
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:";
    private static final String ADD_TASK_MESSAGE = "Got it. I've added this task:";
    private static final String DELETE_TASK_MESSAGE = "Noted. I've removed this task:";
    private static final String FIND_TASK_MESSAGE = "Here are the matching tasks in your list:";

    /**
     * Prints a message indicating file loading error.
     *
     * @return String Message duke returns.
     */
    public String showLoadingError() {
        return "Can't load saved file";
    }

    /**
     * Returns a string which is the user's command.
     *
     * @return users command in String form.
     */
    public String getInstruction() {
        return USER_INPUT.nextLine();
    }


    /**
     * Prints the content of the task list in a form where users can see.
     *
     * @param taskList task list to be printed.
     * @return String Message duke returns.
     */
    public String printArrayList (TaskList taskList) {
        String toReturn = LIST_MESSAGE + "\n";
        for (int i = 0; i < taskList.getSize(); i++) {
            toReturn += String.valueOf(i + 1) + "." + taskList.get(i).toString() + "\n";
        }
        return toReturn;
    }

    /**
     * Prints a greeting for the user on startup.
     *
     * @return String Message duke returns.
     */
    public String greet() {
        String toReturn = GREETING + "\n";
        return toReturn;
    }

    /**
     * Prints a farewell message for the user when 'bye' command is inputted.
     */
    public String sayFarewell() {
        String toReturn = FAREWELL;
        return toReturn;
    }

    /**
     * Prints a notifications which informs the user
     * on which task is completed based on their command.
     *
     * @param task task that is completed.
     * @return String Message duke returns.
     */
    public String completeTaskMessage (Task task) {
        String toReturn = DONE_MESSAGE + "\n" + task.toString();
        return toReturn;
    }

    /**
     * Prints a notification to inform users
     * on which task is found based on their keyword
     *
     * @param tasks tasklist to find tasks in.
     * @return String Message duke returns.
     */
    public String findTaskMessage (TaskList tasks) {
        String toReturn = FIND_TASK_MESSAGE + "\n";
        for (int i = 0; i < tasks.getSize(); i++) {
            toReturn += String.valueOf(i + 1) + "." + tasks.get(i).toString() + "\n";
        }
        return toReturn;
    }

    /**
     * Prints a notification which informs the user
     * on which task is deleted based on their command.
     *
     * @param task task that is deleted.
     * @param size size of the task list.
     * @return String Message duke returns.
     */
    public String deleteTaskMessage (Task task, int size) {
        String toReturn = DELETE_TASK_MESSAGE + "\n";
        toReturn += "   " + task.toString() + "\n";
        toReturn += taskCounterMessage(size) + "\n";
        return toReturn;
    }

    /**
     * Prints a notifcation which informs the user
     * on which task is added based on their command.
     *
     * @param task task that is added.
     * @param size size of the task list.
     * @return String Message duke returns.
     */
    public String addedTaskMessage (Task task, int size) {
        String toReturn = ADD_TASK_MESSAGE + "\n";
        toReturn += "  " + task.toString() + "\n";
        toReturn += taskCounterMessage(size) + "\n";
        return toReturn;
    }

    /**
     * Prints a message of how many task are in the task list.
     *
     * @param size size of task list.
     * @return String Message duke returns.
     */
    public String taskCounterMessage (int size) {
        return "Now you have " + size + " tasks in the list.";
    }

    /**
     * Prints a message informing the user of an error that has occured.
     * Error is specific to the chat bot itself.
     *
     * @param e Duke Exception that has occurred.
     */
    public String printErrorMessage(DukeException e) {
        return e.toString();
    }
}
