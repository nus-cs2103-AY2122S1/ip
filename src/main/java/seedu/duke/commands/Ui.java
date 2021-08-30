package seedu.duke.commands;

import java.util.ArrayList;

import seedu.duke.tasks.Task;

public class Ui {
    public final static String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
    public final static String INDENTED_HORIZONTAL_LINE = "    ____________________________________________________________";
    public final static String INDENT = "     ";
    public final static String INTRODUCTION = "Hello! I'm Ricky\n" + Ui.INDENT + "What can I do for you?";
    public final static String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    public final static String DONE_MESSAGE = "Nice! I've marked this task as done:";
    public final static String LIST_MESSAGE = "Here are the tasks in your list:";
    public final static String ERROR_MSG_UNKOWN_MSG = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public final static String ERROR_MSG_EMPTY_DESCRIPTION = "☹ OOPS!!! The description of a todo cannot be empty.";
    public final static String NO_TASK_MESSAGE = "Good day! You have no task in hand right now.";
    public final static String FIND_ZERO_SIZE = "We are not able to find anything with that keyword. Please try again.";
    public final static String FIND_LIST_MESSAGE = "Here are the matching tasks in your list:";
    public final static String ERROR_MESSAGE_STORAGE_DELETE = "DukeStorageDeleteException: Unable to delete following task from the storage.";
    public final static String ERROR_MESSAGE_STORAGE_UPDATE = "DukeStorageUpdateException: Unable to update data into the storage.";
    public final static String ERROR_MESSAGE_STORAGE_SAVE = "DukeStorageSaveException: Unable to write data into storage.";
    public final static String ERROR_MESSAGE_STORAGE_LOAD = "DukeStorageLoadException: Unable to locate and load the data from storage.";
    public final static String ERROR_MESSAGE_STORAGE_LOAD_OUT_OF_BOUND = "DukeStorageLoadException: Data must have saved in a wrong format.";
    public final static String ERROR_MESSAGE_ACTION_OUT_OF_BOUND = "DukeActionOutOfBoundException: There is no such index from the storage.";

    public static void printMessage(String message) {
        System.out.println(Ui.INDENTED_HORIZONTAL_LINE);
        System.out.println(Ui.INDENT + message);
        System.out.println(Ui.INDENTED_HORIZONTAL_LINE + "\n");
    }

    public static void printIntro() {
        System.out.println(Ui.logo);
        Ui.printMessage(Ui.INTRODUCTION);
    }

    public static void printBye() {
        Ui.printMessage(Ui.BYE_MESSAGE);
    }

    public static void printList(ArrayList<Task> taskList, String zeroSizeMsg, String listMsg) {
        if (taskList.size() == 0) {
            Ui.printMessage(zeroSizeMsg);
            return;
        }
        System.out.println(Ui.INDENTED_HORIZONTAL_LINE);
        System.out.println(Ui.INDENT + listMsg);
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(Ui.INDENT + (i + 1) + ". " + taskList.get(i).toString());
        }
        System.out.println(Ui.INDENTED_HORIZONTAL_LINE + "\n");
    }
}
