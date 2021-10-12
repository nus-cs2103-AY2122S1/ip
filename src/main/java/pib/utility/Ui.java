package pib.utility;

import pib.pibexception.PibException;

/**
 * Class to handle the messages printed to the UI
 */
public class Ui {
    public static final String DIVIDER = "____________________________________________________________\n";

    public String printWelcome() {
        String s = "Hello! I'm pib\n" + "Tell me something!\n";
        System.out.println(s);
        System.out.println(DIVIDER);
        return s;
    }

    public static String printAddSuccess(String taskDescription) {

        String s = "Added: " + taskDescription + "\n";
        System.out.println(s);
        System.out.println(DIVIDER);
        return s;
    }

    public static String printDeleteSuccess(String taskDescription) {
        String s = "Successfully deleted task: " + taskDescription + " \n";
        System.out.println(s);
        System.out.println(DIVIDER);
        return s;
    }

    public static String printMarkAsDoneSuccess(String taskDescription) {
        String s = "Nice! I've marked this task as done:\n" + "[X] " + taskDescription + "\n";
        System.out.println(s);
        System.out.println(DIVIDER);
        return s;
    }

    public static String printList(TaskList list) throws PibException {
        return list.viewList();
    }

    public static String printListSize(TaskList list) {
        return list.viewListSize();
    }

    public static String printDataLoadSuccess() {
        String s = "Saved data successfully loaded!\n";
        System.out.println(s);
        System.out.println(DIVIDER);
        return s;
    }

    public static String printDataLoading() {
        String s = "Saved data loading...\n";
        System.out.println(s);
        return s;
    }

    public static String printNoSavedDataFound() {
        String s = "No saved data found\n";
        System.out.println(s);
        System.out.println(DIVIDER);
        return s;
    }

    public static String printQueryFound(String query) {
        String s = "These tasks contains the word: " + query;
        System.out.println(s);
        return s;
    }

    public static String printQueryNotFound(String query) {
        String s = "No tasks contain the word: " + query + "\n";
        System.out.println(s);
        System.out.println(DIVIDER);
        return s;
    }

    public static String printUpdateSuccessful() {
        String s = "Task successfully updated!";
        System.out.println(s);
        System.out.println(DIVIDER);
        return s;
    }

    public static String printEnd() {
        String s = "Bye! See you next time!\n";
        System.out.println(s);
        System.out.println(DIVIDER);
        return s;
    }

    /**
     * Shows the correct error message to the UI when a PibException is caught
     * 
     * @param error type of error specified by PibException
     */
    public static String printError(String error) {
        switch (error) {
        case "empty-list":
            String s1 = "Task list is currently empty\n";
            System.out.println(s1);
            System.out.println(DIVIDER);
            return s1;
        case "empty-task-description":
            String s2 = "Task description can't be blank\n";
            System.out.println(s2);
            System.out.println(DIVIDER);
            return s2;
        case "empty-query":
            String s3 = "Tell me what word you are looking for!\n";
            System.out.println(s3);
            System.out.println(DIVIDER);
            return s3;
        case "d-wrong-format":
            String s4 = "Please format the command as: deadline <task> /by <yyyy-mm-dd> <hhmm>\n";
            System.out.println(s4);
            System.out.println(DIVIDER);
            return s4;
        case "e-wrong-format":
            String s5 = "Please format the command as: event <task> /at <yyyy-mm-dd> <hhmm>\n";
            System.out.println(s5);
            System.out.println(DIVIDER);
            return s5;
        case "wrong-datetime-format":
            String s6 = "Ensure date-time format is YYYY-MM-DD HHMM\n";
            System.out.println(s6);
            System.out.println(DIVIDER);
            return s6;
        case "blank-markasdone-index":
            String s7 = "Tell me which item to mark as done!\n";
            System.out.println(s7);
            System.out.println(DIVIDER);
            return s7;
        case "ioob-exception":
            String s8 = "Please enter a valid task number!\n";
            System.out.println(s8);
            System.out.println(DIVIDER);
            return s8;
        case "blank-delete-index":
            String s9 = "Tell me which item to delete!\n";
            System.out.println(s9);
            System.out.println(DIVIDER);
            return s9;
        case "already-markedasdone":
            String s10 = "Item is already marked as done!\n";
            System.out.println(s10);
            System.out.println(DIVIDER);
            return s10;
        case "error-loading":
            String s11 = "Error loading saved data\n";
            System.out.println(s11);
            System.out.println(DIVIDER);
            return s11;
        case "error-saving":
            String s12 = "Error saving data\n";
            System.out.println(s12);
            System.out.println(DIVIDER);
            return s12;
        case "fnf-exception":
            String s13 = "Error locating file\n";
            System.out.println(s13);
            System.out.println(DIVIDER);
            return s13;
        case "io-exception":
            String s14 = "IOException\n";
            System.out.println(s14);
            System.out.println(DIVIDER);
            return s14;
        case "unknown-command":
            String s15 = "Uh oh :( I don't know that command\n";
            System.out.println(s15);
            System.out.println(DIVIDER);
            return s15;
        case "edit-wrong-format":
            String s16 = "Please format command as: edit <task number> </i or /d or /t> <new value>";
            System.out.println(s16);
            System.out.println(DIVIDER);
            return s16;
        case "no-todo-date":
            String s17 = "Todo tasks do not contain date";
            System.out.println(DIVIDER);
            return s17;
        case "invalid-update-part":
            String s18 = "Use /i to update task Information, /d to update task Date and /t to update task Time";
            System.out.println(DIVIDER);
            return s18;
        case "empty-new-value":
            String s19 = "New value cannot be blank";
            System.out.println(DIVIDER);
            return s19;
        case "wrong-edit-datetime-format":
            String s20 = "Ensure date format is YYYY-MM-DD or time format is HHMM\n";
            System.out.println(20);
            System.out.println(DIVIDER);
            return s20;
        default:
            String defaultError = "Error Occurred!";
            System.out.println(defaultError);
            System.out.println(DIVIDER);
            return defaultError;
        }
    }
}
