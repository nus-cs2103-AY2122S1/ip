package pib.utility;

import static pib.utility.Messages.DIVIDER;
import static pib.utility.Messages.ERROR_ALREADY_MARKED_AS_DONE;
import static pib.utility.Messages.ERROR_BLANK_DELETE_INDEX;
import static pib.utility.Messages.ERROR_BLANK_MARK_AS_DONE_INDEX;
import static pib.utility.Messages.ERROR_DATETIME_WRONG_FORMAT;
import static pib.utility.Messages.ERROR_DEADLINE_WRONG_FORMAT;
import static pib.utility.Messages.ERROR_DEFAULT;
import static pib.utility.Messages.ERROR_EDIT_DATETIME_WRONG_FORMAT;
import static pib.utility.Messages.ERROR_EDIT_WRONG_FORMAT;
import static pib.utility.Messages.ERROR_EMPTY_LIST;
import static pib.utility.Messages.ERROR_EMPTY_NEW_VALUE;
import static pib.utility.Messages.ERROR_EMPTY_QUERY;
import static pib.utility.Messages.ERROR_EMPTY_TASK_DESCRIPTION;
import static pib.utility.Messages.ERROR_EVENT_WRONG_FORMAT;
import static pib.utility.Messages.ERROR_FNF_EXCEPTION;
import static pib.utility.Messages.ERROR_INVALID_UPDATE_PART;
import static pib.utility.Messages.ERROR_IOOB_EXCEPTION;
import static pib.utility.Messages.ERROR_IO_EXCEPTION;
import static pib.utility.Messages.ERROR_LOADING_SAVED_DATA;
import static pib.utility.Messages.ERROR_NO_TODO_DATE;
import static pib.utility.Messages.ERROR_SAVING_DATA;
import static pib.utility.Messages.ERROR_UNKNOWN_COMMAND;

import pib.pibexception.PibException;

/**
 * Class to handle the messages printed to the UI
 */
public class Ui {

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

    private static String showError(String errorMsg) {
        System.out.println(errorMsg);
        System.out.println(DIVIDER);
        return errorMsg;
    }

    /**
     * Shows the correct error message to the UI when a PibException is caught
     *
     * @param error type of error specified by PibException
     */
    public static String printError(String error) {
        switch (error) {
        case "empty-list":
            return showError(ERROR_EMPTY_LIST);
        case "empty-task-description":
            return showError(ERROR_EMPTY_TASK_DESCRIPTION);
        case "empty-query":
            return showError(ERROR_EMPTY_QUERY);
        case "d-wrong-format":
            return showError(ERROR_DEADLINE_WRONG_FORMAT);
        case "e-wrong-format":
            return showError(ERROR_EVENT_WRONG_FORMAT);
        case "wrong-datetime-format":
            return showError(ERROR_DATETIME_WRONG_FORMAT);
        case "blank-markasdone-index":
            return showError(ERROR_BLANK_MARK_AS_DONE_INDEX);
        case "ioob-exception":
            return showError(ERROR_IOOB_EXCEPTION);
        case "blank-delete-index":
            return showError(ERROR_BLANK_DELETE_INDEX);
        case "already-markedasdone":
            return showError(ERROR_ALREADY_MARKED_AS_DONE);
        case "error-loading":
            return showError(ERROR_LOADING_SAVED_DATA);
        case "error-saving":
            return showError(ERROR_SAVING_DATA);
        case "fnf-exception":
            return showError(ERROR_FNF_EXCEPTION);
        case "io-exception":
            return showError(ERROR_IO_EXCEPTION);
        case "unknown-command":
            return showError(ERROR_UNKNOWN_COMMAND);
        case "edit-wrong-format":
            return showError(ERROR_EDIT_WRONG_FORMAT);
        case "no-todo-date":
            return showError(ERROR_NO_TODO_DATE);
        case "invalid-update-part":
            return showError(ERROR_INVALID_UPDATE_PART);
        case "empty-new-value":
            return showError(ERROR_EMPTY_NEW_VALUE);
        case "wrong-edit-datetime-format":
            return showError(ERROR_EDIT_DATETIME_WRONG_FORMAT);
        default:
            assert false;
            return showError(ERROR_DEFAULT);
        }
    }
}
