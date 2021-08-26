package gnosis.ui;

import gnosis.controller.GnosisController;
import gnosis.model.Task;
import gnosis.util.GnosisConstants;
import gnosis.util.GnosisException;

import java.util.List;
import java.util.Scanner;

// View Layer

public class GnosisUI {

    private boolean isListeningInput = false;

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
    
    public boolean isStillListeningInput() {
        return this.isListeningInput;
    }

    public void stopListeningInput() {
        this.isListeningInput = false;
    }

    public void displayGreetMessage(boolean isDataAvailable) {
        displayTopDivider();

        System.out.println(GnosisConstants.GREET_MESSAGE);
        System.out.println(
                isDataAvailable
                ? GnosisConstants.DATA_TASK_FILE_FOUND_MESSAGE
                : GnosisConstants.DATA_TASK_FILE_NOT_FOUND_MESSAGE);

        displayBottomDivider();
    }

    public void updateTaskManagementViewMessage(String action, Task task, int numOfTasks) {
        System.out.println("gnosis.main.model.Task " + action + ":");
        System.out.println(task);
        System.out.println("Total tasks in the list: " + numOfTasks);
        displayBottomDivider();
    }

    public void displayAllTasksMessage(List<Task> tasks) {
        int len = tasks.size();
        if (len == 0) {
            System.out.println("There is no gnosis.task in the list.");
        } else {
            System.out.println("Listing all tasks in list:");
            for (int i = 0; i < len; i++) {
                System.out.println((i+1) + ". " + tasks.get(i));
            }
        }
        displayBottomDivider();
    }

    public void displayMarkedTaskMessage(Task task, int taskIndex) {
        System.out.println("gnosis.main.model.Task " + (taskIndex) +" marked as done:" );
        System.out.println("\t" + task);
        displayBottomDivider();
    }

    public void displayMessage(String message) {
        System.out.println(message);
        displayBottomDivider();
    }
    public void displayTopDivider() {
        System.out.println(GnosisConstants.DISPLAY_FORMAT);
    }
    public void displayBottomDivider() {
        System.out.println(GnosisConstants.DISPLAY_FORMAT + '\n');
    }
    public void displayByeMessage() {
        System.out.println(GnosisConstants.BYE_MESSAGE);
    }
}
