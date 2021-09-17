package sun.ui;

import sun.data.messages.Messages;

/**
 * Class that deals with interactions with the user of Sun.
 *
 * @author Won Ye Ji
 */
public class Ui {

    /**
     * Greets the user.
     *
     * @return Welcome message to the user.
     */
    public static String greet() {
        return Messages.WELCOME_MESSAGE + "\n" + "Enter 'help' to see the commands I can handle.";
    }

    /**
     * Returns farewell message to the user.
     *
     * @return Farewell message to the user.
     */
    public static String bye() {
        return Messages.BYE_MESSAGE;
    }

    /**
     * Informs the user that they have entered an unidentifiable command.
     *
     * @return Error message to the user.
     */
    public static String getInputUnknownError() {
        return Messages.UNKNOWN_INPUT_MESSAGE;
    }

    /**
     * Informs the user that they have not entered a valid date.
     *
     * @return Error message to the user.
     */
    public static String getDateMissingError() {
        return Messages.MISSING_DATE;
    }

    /**
     * Informs the user that list has been printed.
     *
     * @return List printed message.
     */
    public static String getPrintListMessage() {
        return Messages.PRINT_LIST_MESSAGE;
    }

    /**
     * Informs the user that archives have been printed.
     *
     * @return Archives printed message.
     */
    public static String getPrintArchivesMessage() {
        return Messages.PRINT_ARCHIVES_MESSAGE;
    }

    /**
     * Informs the user the number of tasks on their list.
     *
     * @param i Number of tasks.
     * @return Number of tasks message.
     */
    public static String getPrintNoOfTasksMessage(int i) {
        return String.format(Messages.NUMBER_OF_TASKS_MESSAGE, i);
    }

    /**
     * Informs the user that the index is out of bounds.
     *
     * @param i Maximum index.
     * @return Index out of bounds message.
     */
    public static String getIndexOutOfBoundsError(int i) {
        return String.format(Messages.INDEX_OUT_OF_BOUNDS_ERROR, i);
    }

    /**
     * Informs the user that the task has already been marked as done.
     *
     * @return Already marked as done message.
     */
    public static String getAlreadyMarkedAsDoneError() {
        return Messages.ALREADY_MARKED_AS_DONE_ERROR;
    }

    /**
     * Informs the user that the task has been marked as done.
     *
     * @return Marked as done message.
     */
    public static String getMarkAsDoneMessage() {
        return Messages.MARK_TASK_AS_DONE_MESSAGE;
    }

    /**
     * Informs the user that the task has been added.
     *
     * @return Task added message.
     */
    public static String getAddTaskMessage() {
        return Messages.ADD_TASK;
    }

    /**
     * Informs the user that the task has been archived.
     *
     * @return Task archived message.
     */
    public static String getArchiveTaskMessage() {
        return Messages.ARCHIVE_TASK;
    }

    /**
     * Informs the user that the task has been deleted.
     *
     * @return Task deleted message.
     */
    public static String getDeleteTaskMessage() {
        return Messages.DELETE_TASK;
    }

    /**
     * Prints an empty list message.
     *
     * @return Empty list message,
     */
    public static String getPrintEmptyListMessage() {
        return Messages.EMPTY_LIST;
    }

    /**
     * Prints tasks found message.
     *
     * @return Tasks found message.
     */
    public static String getPrintFoundTasksMessage() {
        return Messages.MATCHING_TASK_FOUND;
    }

    /**
     * Returns no tasks found message.
     *
     * @return No such tasks found message.
     */
    public static String getNoSuchTasksFoundMessage() {
        return Messages.NO_MATCHING_TASK_FOUND;
    }

    /**
     * Returns the user guide.
     *
     * @return The user guide.
     */
    public static String getUserGuide() {
        return Messages.HELP;
    }

    /**
     * Returns an error message indicating date format error.
     *
     * @return An error message indicating date format error.
     */
    public static String getDateFormatError() {
        return Messages.DATE_FORMAT_ERROR;
    }

    /**
     * Returns an error message indicating command format error.
     *
     * @param s Type of command.
     * @return An error message indicating command format error.
     */
    public static String getCommandFormatError(String s) throws AssertionError {
        String error;
        switch(s) {
        case "archive":
            error = Messages.ARCHIVE_FORMAT_ERROR;
            break;
        case "delete":
            error = Messages.DELETE_FORMAT_ERROR;
            break;
        case "done":
            error = Messages.DONE_FORMAT_ERROR;
            break;
        case "todo":
            error = Messages.EMPTY_TODO_DESC;
            break;
        case "deadline":
            error = Messages.EMPTY_DEADLINE_DESC;
            break;
        case "event":
            error = Messages.EMPTY_EVENT_DESC;
            break;
        case "find":
            error = Messages.EMPTY_FIND_CMD;
            break;
        default:
            throw new AssertionError("No such command.");
        }
        return error;
    }

    /**
     * Returns an error message indicating missing index error.
     *
     * @param s Type of command.
     * @return An error message indicating missing index error.
     */
    public static String getMissingIndexError(String s) throws AssertionError {
        String error;
        switch(s) {
        case "archive":
            error = Messages.ARCHIVE_MISSING_INDEX_ERROR;
            break;
        case "delete":
            error = Messages.DELETE_MISSING_INDEX_ERROR;
            break;
        case "done":
            error = Messages.DONE_MISSING_INDEX_ERROR;
            break;
        default:
            throw new AssertionError("No such command.");
        }
        return error;
    }

    /**
     * Returns an error message indicating list has no tasks error.
     *
     * @param s Type of command.
     * @return An error message indicating list had no tasks error.
     */
    public static String getNoTasksError(String s) throws AssertionError {
        String error;
        switch(s) {
        case "archive":
            error = Messages.ARCHIVE_NO_TASKS_ERROR;
            break;
        case "delete":
            error = Messages.DELETE_NO_TASKS_ERROR;
            break;
        case "done":
            error = Messages.DONE_NO_TASKS_ERROR;
            break;
        default:
            throw new AssertionError("No such command.");
        }
        return error;
    }

    /**
     * Prints an indentation.
     *
     * @return an Indentation.
     */
    public static String getIndentation() {
        return Messages.INDENTATION;
    }
}
