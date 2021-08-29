package pib;

import pib.pibexception.PibException;

/**
 * Class to handle the messages printed to the UI
 */
public class Ui {
    public static final String DIVIDER = "____________________________________________________________\n";

    public void printWelcome() {
        System.out.println("Hello! I'm pib\n" + "Tell me something!\n");
        System.out.println(DIVIDER);
    }

    public static void printAddSuccess(String taskDescription) {
        System.out.println("Added: " + taskDescription + "\n");
        System.out.println(DIVIDER);
    }

    public static void printDeleteSuccess(String taskDescription) {
        System.out.println("Successfully deleted task: " + taskDescription + " \n");
        System.out.println(DIVIDER);
    }

    public static void printMarkAsDoneSuccess(String taskDescription) {
        System.out.println("Nice! I've marked this task as done:\n" + "[X] " + taskDescription + "\n");
        System.out.println(DIVIDER);
    }

    public static void printList(TaskList list) throws PibException {
        list.viewList();
    }

    public static void printListSize(TaskList list) {
        list.viewListSize();
    }

    public static void printDataLoadSuccess() {
        System.out.println("Saved data successfully loaded!\n" + DIVIDER);
    }

    public static void printDataLoading() {
        System.out.println("Saved data loading...");
    }

    public static void printNoSavedDataFound() {
        System.out.println("No saved data found\n" + DIVIDER);
    }

    public static void printQueryFound(String query) {
        System.out.println("These task contains the word: " + query);
    }

    public static void printQueryNotFound(String query) {
        System.out.println("No tasks contain the word: "  + query + "\n" + DIVIDER);
    }

    public static void printEnd() {
        System.out.println("Bye! See you next time!\n");
        System.out.println(DIVIDER);
    }

    /**
     * Show the correct error message to the UI when a PibException is caught
     * @param error type of error specified by PibException
     */
    public static void printError(String error) {
        switch (error) {
        case "empty-list":
            System.out.println("Task list is currently empty\n");
            break;
        case "empty-task-description":
            System.out.println("Task description can't be blank\n");
            break;
        case "empty-query":
            System.out.println("Tell me what word you are looking for!\n");
            break;
        case "d-wrong-format":
            System.out.println("Please format the command as: deadline <task> /by <yyyy-mm-dd> <hhmm>\n");
            break;
        case "e-wrong-format":
            System.out.println("Please format the command as: event <task> /at <yyyy-mm-dd> <hhmm>\n");
            break;
        case "wrong-datetime-format":
            System.out.println("Ensure date-time format is YYYY-MM-DD HHMM\n");
            break;
        case "blank-markasdone-index":
            System.out.println("Tell me which item to mark as done!\n");
            break;
        case "ioob-exception":
            System.out.println("Please enter a valid task number!\n");
            break;
        case "blank-delete-index":
            System.out.println("Tell me which item to delete!\n");
            break;
        case "already-markedasdone":
            System.out.println("Item is already marked as done!\n");
            break;
        case "error-loading":
            System.out.println("Error loading saved data\n");
            break;
        case "error-saving":
            System.out.println("Error saving data\n");
            break;
        case "fnf-exception":
            System.out.println("Error locating file\n");
            break;
        case "io-exception":
            System.out.println("IOException\n");
            break;
        case "unknown-command":
            System.out.println("Uh oh :( I don't know that command\n");
            break;
        default:
            System.out.println("Error Occurred!");
            break;
        }
        System.out.println(DIVIDER);
    }
}
