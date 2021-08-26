package gnosis;

import model.Task;
import util.GnosisConstants;
import java.util.List;
import java.util.Scanner;

/**
 * The GnosisUI handles the user interaction with user.
 * It focuses on listening for input and displaying output.
 *
 * @author Pawandeep Singh
 * */
public class GnosisUI {

    /** Specifies whether view is listening for user input */
    private boolean isListeningInput = false;

    /**
     * gnosisView starts to listen and wait for user input.
     *
     * @param gc To process user input.
     * @param sc To listen for user input.
     */
    public void listenForInput(GnosisController gc, Scanner sc) {
        this.isListeningInput = true;
        try {
            String command = sc.next();
            String input = sc.nextLine();
            gc.executeCommand(command,input);

        } catch (GnosisException ge) {
            displayMessage(ge.toString());
        } catch (NumberFormatException nfe) {
            displayMessage(GnosisConstants.DONE_COMMAND_NUM_INPUT_EXCEPT_MESSAGE);
        }
    }

    /**
     * Retrieves whether view is still listening for user input.
     * True -> view is still listening for input
     * false -> view is NOT still listening for input
     *
     * @return True/False determines if view is still listening for input
     */
    public boolean isStillListeningInput() {
        return this.isListeningInput;
    }

    /**
     * Stops view from listening to input.
     */
    public void stopListeningInput() {
        this.isListeningInput = false;
    }

    /**
     * Displays greeting message to user with message of
     * whether file system is available or not.
     *
     * @param isDataAvailable Whether file system is available or not.
     */
    public void displayGreetMessage(boolean isDataAvailable) {
        displayTopDivider();
        System.out.println(GnosisConstants.GREET_MESSAGE);
        System.out.println(isDataAvailable ? GnosisConstants.DATA_TASK_FILE_FOUND_MESSAGE
                : GnosisConstants.DATA_TASK_FILE_NOT_FOUND_MESSAGE);
        displayBottomDivider();
    }


    /**
     * Displays Task view message from specified task command.
     *
     * @param action - Command user has specified
     * @param task - specified task as a result of user command
     * @param numOfTasks - number of task
     */
    public void updateTaskManagementViewMessage(String action, Task task, int numOfTasks) {
        System.out.println("model.Task " + action + ":");
        System.out.println(task);
        System.out.println("Total tasks in the list: " + numOfTasks);
        displayBottomDivider();
    }

    /**
     * Displays all tasks to user.
     *
     * @param tasks - list of all tasks
     */
    public void displayAllTasksMessage(List<Task> tasks) {
        int len = tasks.size();
        if (len == 0) {
            System.out.println("There is no task in the list.");
        } else {
            System.out.println("Listing all tasks in list:");
            for (int i = 0; i < len; i++) {
                System.out.println((i+1) + ". " + tasks.get(i));
            }
        }
        displayBottomDivider();
    }

    /**
     * Displays task marked status to user.
     *
     * @param task - specified task to display for mark status
     * @param taskIndex - task number from task list
     */
    public void displayMarkedTaskMessage(Task task, int taskIndex) {
        System.out.println("model.Task " + (taskIndex) +" marked as done:" );
        System.out.println("\t" + task);
        displayBottomDivider();
    }

    /**
     * Displays specified message
     *
     * @param message Message to display to user.
     */
    public void displayMessage(String message) {
        System.out.println(message);
        displayBottomDivider();
    }

    /**
     * Displays top divider output.
     *
     */
    public void displayTopDivider() {
        System.out.println(GnosisConstants.DISPLAY_FORMAT);
    }

    /**
     * Displays botoom divider output.
     *
     */
    public void displayBottomDivider() {
        System.out.println(GnosisConstants.DISPLAY_FORMAT + '\n');
    }

    /**
     * Displays bye message to user.
     *
     */
    public void displayByeMessage() {
        System.out.println(GnosisConstants.BYE_MESSAGE);
    }
}
