package duke.ui;

import duke.data.messages.Messages;

/**
 * Class that deals with interactions with the user of Duke.
 *
 * @author Wang Hong Yong
 */
public class Ui {

    private static final String DIVIDER = "____________________________________________________________";
    private static final String TAB = "  ";
    private static final String LS = System.lineSeparator();


    public static void prettify(String message){
        System.out.println(TAB + DIVIDER);
        System.out.println(message);
        System.out.println(TAB + DIVIDER);
    }

    /**
     * Welcomes the user.
     */
    public static void printWelcomeMsg() {
        String message =  TAB + TAB + Messages.MESSAGE_WELCOME_1 + LS + TAB + TAB + Messages.MESSAGE_WELCOME_2;
        prettify(message);
    }

    /**
     * Says bye the user.
     */
    public static void printGoodbyeMsg() {
        prettify(Messages.MESSAGE_GOODBYE);
    }

    /**
     * Informs the user that they have inputted an empty task.
     * @param type Type of task input.
     * @return The error message to the user.
     */
    public static String getEmptyDescriptionMsg(String type) {
        String errorMsg = TAB + TAB + "☹ OOPS!!! ";
        switch(type) {
            case "todo":
                errorMsg += Messages.MESSAGE_EMPTY_TODO;
                break;
            case "deadline":
                errorMsg += Messages.MESSAGE_EMPTY_DEADLINE;

                break;
            case "event":
                errorMsg += Messages.MESSAGE_EMPTY_EVENT;
                break;
            case "delete":
                errorMsg += Messages.MESSAGE_EMPTY_DELETE;
                break;
            case "done":
                errorMsg += Messages.MESSAGE_EMPTY_DONE;
        }
        return errorMsg;
    }

    /**
     * Informs the user that they have inputted unknown command.
     * @return The error message to the user.
     */
    public static String getUnknownInputMsg() {
        return TAB + TAB + Messages.MESSAGE_INPUT_UNKNOWN;
    }

    /**
     * Informs the user that it failed to create directory.
     */
    public static void printCreateDirectoryErr() {
        System.err.println("IOException: unable to create directory");
    }

    /**
     * Informs the user that they have encountered IOException.
     * @return The error IOException message to the user.
     */
    public static String getIoMsg(){
        return Messages.MESSAGE_IO_ERR;
    }

    /**
     * Informs the user that task has been added.
     *
     * @param task String representing task
     * @param num Integer representing number of tasks left
     */
    public static void printAddMsg(String task, Integer num) {
        String msg = Messages.MESSAGE_ADD + LS + TAB + TAB + TAB + task + LS +
                String.format(Messages.MESSAGE_TASKS_LEFT, num);
        prettify(msg);
    }

    /**
     * Informs the user that task has been removed.
     *
     * @param task String representing task
     * @param num Integer representing number of tasks left
     */
    public static void printRemoveMsg(String task, Integer num) {
        String msg = Messages.MESSAGE_REMOVE + LS + TAB + TAB + TAB + task + LS +
                String.format(Messages.MESSAGE_TASKS_LEFT, num);
        prettify(msg);
    }

    /**
     * Informs the user that task has been marked as done.
     *
     * @param task String representing task
     */
    public static void printTaskDone(String task) {
        String msg = TAB + Messages.MESSAGE_DONE + LS + TAB + TAB + task;
        prettify(msg);
    }
}
