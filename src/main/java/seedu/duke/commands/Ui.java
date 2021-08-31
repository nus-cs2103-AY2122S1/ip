package seedu.duke.commands;

import java.util.ArrayList;

import seedu.duke.tasks.Task;

public class Ui {
    public static final String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
    // public static final String INDENTED_HORIZONTAL_LINE = "
    // ____________________________________________________________";
    // public static final String INDENT = " ";
    // public static final String INTRODUCTION = "Hello! I'm Ricky\n" + Ui.INDENT +
    // "What can I do for you?";
    public static final String INTRODUCTION = "Hello! I'm Ricky\n" + "What can I do for you?";
    public static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String DONE_MESSAGE = "Nice! I've marked this task as done:";
    public static final String LIST_MESSAGE = "Here are the tasks in your list:";
    public static final String ERROR_MSG_UNKOWN_MSG = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String ERROR_MSG_EMPTY_DESCRIPTION = "☹ OOPS!!! The description of a todo cannot be empty.";
    public static final String NO_TASK_MESSAGE = "Good day! You have no task in hand right now.";
    public static final String FIND_ZERO_SIZE = "We are not able to find anything with that keyword. Please try again.";
    public static final String FIND_LIST_MESSAGE = "Here are the matching tasks in your list:";
    public static final String ERROR_MESSAGE_STORAGE_DELETE = "DukeStorageDeleteException: Unable to delete following task from the storage.";
    public static final String ERROR_MESSAGE_STORAGE_UPDATE = "DukeStorageUpdateException: Unable to update data into the storage.";
    public static final String ERROR_MESSAGE_STORAGE_SAVE = "DukeStorageSaveException: Unable to write data into storage.";
    public static final String ERROR_MESSAGE_STORAGE_LOAD = "DukeStorageLoadException: Unable to locate and load the data from storage.";
    public static final String ERROR_MESSAGE_STORAGE_LOAD_OUT_OF_BOUND = "DukeStorageLoadException: Data must have saved in a wrong format.";
    public static final String ERROR_MESSAGE_ACTION_OUT_OF_BOUND = "DukeActionOutOfBoundException: There is no such index from the storage.";

    /**
     * Helps to print out the common Ui Messages with proper formatting.
     * 
     * @param message is the message which will be printed.
     */
    public static String printMessage(String message) {
        String appendedLine = message + "\n";
        return appendedLine;
    }

    /**
     * Prints the introduction message.
     */
    public static void printIntro() {
        System.out.println(Ui.logo);
        Ui.printMessage(Ui.INTRODUCTION);
    }

    /**
     * Prints the bye message.
     */
    public static String printBye() {
        return Ui.printMessage(Ui.BYE_MESSAGE);
    }

    /**
     * Prints the list of tasks with numbering from the given taskList passed to
     * this function.
     * 
     * @param taskList    is the list of tasks that will be printed out into a list.
     * @param zeroSizeMsg is the message when the size of the list is zero.
     * @param listMsg     message when the size of list is not zero.
     */
    public static String printList(ArrayList<Task> taskList, String zeroSizeMsg, String listMsg) {
        String message = "";

        if (taskList.size() == 0) {
            return Ui.printMessage(zeroSizeMsg);
        }
        // message = Ui.INDENTED_HORIZONTAL_LINE + "\n" + Ui.INDENT + listMsg + "\n";
        message = listMsg + "\n";
        for (int i = 0; i < taskList.size(); i++) {
            // message += Ui.INDENT + (i + 1) + ". " + taskList.get(i).toString() + "\n";
            message += (i + 1) + ". " + taskList.get(i).toString() + "\n";
        }
        // message += Ui.INDENTED_HORIZONTAL_LINE + "\n\n";
        return message;
    }
}
