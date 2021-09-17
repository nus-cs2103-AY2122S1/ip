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

    /**
     * Welcomes the user.
     *
     * @return String representation of welcome message.
     */
    public static String getWelcomeMsg() {
        String message = TAB + TAB + Messages.MESSAGE_WELCOME_1 + LS + TAB + TAB + Messages.MESSAGE_WELCOME_2;
        return message;
    }

    /**
     * Says bye the user.
     *
     * @return String representation of bye message.
     */
    public static String getGoodbyeMsg() {
        return Messages.MESSAGE_GOODBYE;
    }

    /**
     * Returns deadline format message.
     *
     * @return String representation of deadline format message.
     */
    public static String getDeadlineFormatMsg() {
        return Messages.MESSAGE_DEADLINE_FORMAT;
    }

    /**
     * Returns delete format message.
     *
     * @return String representation of delete format message.
     */
    public static String getDeleteFormatMsg() {
        return Messages.MESSAGE_DELETE_FORMAT;
    }

    /**
     * Returns done format message.
     *
     * @return String representation of done format message.
     */
    public static String getDoneFormatMsg() {
        return Messages.MESSAGE_DONE_FORMAT;
    }

    /**
     * Returns event format message.
     *
     * @return String representation of event format message.
     */
    public static String getEventFormatMsg() {
        return Messages.MESSAGE_EVENT_FORMAT;
    }

    /**
     * Returns tag format message.
     *
     * @return String representation of tag format message.
     */
    public static String getTagFormatMsg() {
        return Messages.MESSAGE_TAG_FORMAT;
    }

    /**
     * Returns todo format message.
     *
     * @return String representation of todo format message.
     */
    public static String getTodoFormatMsg() {
        return Messages.MESSAGE_TODO_FORMAT;
    }

    /**
     * Returns task already completed message.
     *
     * @return String representation that task is already completed.
     */
    public static String getTaskAlreadyDoneMsg() {
        return Messages.MESSAGE_TASK_ALREADY_DONE;
    }

    /**
     * Returns error message of negative input for task.
     *
     * @return String representation of negative input task message.
     */
    public static String getNegativeTaskMsg() {
        return Messages.MESSAGE_TASK_NEGATIVE;
    }

    /**
     * Returns task not found message.
     *
     * @return String representation of task not found message.
     */
    public static String getTaskNotFoundMsg() {
        return Messages.MESSAGE_TASK_NOT_FOUND;
    }

    /**
     * Returns error that list is empty message.
     *
     * @return String representation of empty list message.
     */
    public static String getListEmptyMsg() {
        return Messages.MESSAGE_LIST_EMPTY;
    }

    /**
     * Informs the user that they have inputted an empty task.
     * @param type Type of task input.
     * @return String of error message to the user.
     */
    public static String getEmptyDescriptionMsg(String type) {
        String errorMsg = "☹ OOPS!!! ";
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
            break;
        case "find":
            errorMsg += Messages.MESSAGE_EMPTY_FIND;
            break;
        default:
            break;
        }
        return errorMsg;
    }

    /**
     * Informs the user that they have inputted unknown command.
     *
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
     *
     * @return The error IOException message to the user.
     */
    public static String getIoMsg() {
        return Messages.MESSAGE_IO_ERR;
    }

    /**
     * Informs the user that task has been added.
     *
     * @param task String representing task.
     * @param num Integer representing number of tasks left.
     * @return String representation of add task message.
     */
    public static String getAddMsg(String task, Integer num) {
        String msg = Messages.MESSAGE_ADD + LS + TAB + TAB + TAB + task + LS
                + String.format(Messages.MESSAGE_TASKS_LEFT, num);
        return msg;
    }

    /**
     * Informs the user that tag has been added.
     *
     * @param task String representing all the tags.
     * @return String representation of tag added message.
     */
    public static String getAddTagMsg(String task) {
        String msg = Messages.MESSAGE_TAG + LS + TAB + TAB + TAB + task + LS;
        return msg;
    }

    /**
     * Informs the user that task has not been found.
     *
     * @return String representation of no task found.
     */
    public static String getNoSuchTaskMsg() {
        return Messages.MESSAGE_NO_TASKS_FOUND;
    }

    /**
     * Prints the message after item is removed.
     *
     * @param task String representing task
     * @param num Integer representing number of tasks left
     * @return String representation of remove task message.
     */
    public static String getRemoveMsg(String task, Integer num) {
        String msg = Messages.MESSAGE_REMOVE + LS + TAB + TAB + TAB + task + LS
                + String.format(Messages.MESSAGE_TASKS_LEFT, num);
        return msg;
    }

    /**
     * Informs the user that task has been marked as done.
     *
     * @param task String representing task
     * @return String representing the task done.
     */
    public static String getTaskDone(String task) {
        String msg = TAB + Messages.MESSAGE_DONE + LS + TAB + TAB + task;
        return msg;
    }
}
