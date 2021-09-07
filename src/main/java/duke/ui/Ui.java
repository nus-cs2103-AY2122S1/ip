package duke.ui;

import duke.data.messages.Messages;

/**
 * Class that deals with interactions with the user of Duke.
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
        return Messages.WELCOME_MESSAGE + "\n" + userGuide();
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
     * Informs the user that they have inputted an empty task.
     *
     * @param s Type of task input.
     * @return The error message to the user.
     */
    public static String emptyDescription(String s) {
        String error = "OOPS!!!";
        switch(s) {
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
            error = error;
        }
        return error;
    }

    /**
     * Informs the user that they have entered an unidentifiable command.
     *
     * @return Error message to the user.
     */
    public static String inputUnknown() {
        return Messages.UNKNOWN_INPUT_MESSAGE;
    }

    /**
     * Informs the user that they have not entered a valid date.
     *
     * @return Error message to the user.
     */
    public static String dateMissing() {
        return Messages.MISSING_DATE;
    }

    /**
     * Informs the user that list has been printed.
     *
     * @return List printed message.
     */
    public static String printList() {
        return Messages.PRINT_LIST_MESSAGE;
    }

    /**
     * Informs the user the number of tasks on their list.
     *
     * @param i Number of tasks.
     * @return Number of tasks message.
     */
    public static String printNoOfTasks(int i) {
        return String.format(Messages.NUMBER_OF_TASKS_MESSAGE, i);
    }

    /**
     * Informs the user that the task has been marked as done.
     *
     * @return Marked as done message.
     */
    public static String markAsDone() {
        return Messages.MARK_TASK_AS_DONE_MESSAGE;
    }

    /**
     * Informs the user that the task has been added.
     *
     * @return Task added message.
     */
    public static String addTask() {
        return Messages.ADD_TASK;
    }

    /**
     * Informs the user that the task has been deleted.
     *
     * @return Task deleted message.
     */
    public static String deleteTask() {
        return Messages.DELETE_TASK;
    }

    /**
     * Prints an empty list message.
     *
     * @return Empty list message,
     */
    public static String printEmptyList() {
        return Messages.EMPTY_LIST;
    }

    /**
     * Prints tasks found message.
     *
     * @return Tasks found message.
     */
    public static String printFoundTasks() {
        return Messages.MATCHING_TASK_FOUND;
    }

    /**
     * Returns no tasks found message.
     *
     * @return No such tasks found message.
     */
    public static String noSuchTasksFound() {
        return Messages.NO_MATCHING_TASK_FOUND;
    }

    /**
     * Returns the user guide.
     *
     * @return The user guide.
     */
    public static String userGuide() {
        return Messages.HELP;
    }

    /**
     * Returns an error message indicating date format error.
     *
     * @return an error message indicating date format error.
     */
    public static String dateFormatError() {
        return Messages.DATE_FORMAT_ERROR;
    }

    /**
     * Prints an indentation.
     *
     * @return an Indentation.
     */
    public static String indentation() {
        return Messages.INDENTATION;
    }
}
