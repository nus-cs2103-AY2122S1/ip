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
     */
    public static void greet() {
        System.out.println(Messages.WELCOME_MESSAGE);
    }

    /**
     * Farewell message to the user.
     */
    public static void bye() {
        System.out.println(Messages.INDENTATION + Messages.BYE_MESSAGE);
    }

    /**
     * Informs the user that they have inputted an empty task.
     * @param s Type of task input.
     * @return The error message to the user.
     */
    public static String emptyDescription(String s) {
        String error = "OOPS!!!";
        switch(s) {
        case "todo":
            error = Messages.INDENTATION + "OOPS!!! The description of a todo cannot be empty.";
            break;
        case "deadline":
            error = Messages.INDENTATION + "OOPS!!! The description of a deadline cannot be empty.";
            break;
        case "event":
            error = Messages.INDENTATION + "OOPS!!! The description of an event cannot be empty.";
            break;
        case "find":
            error = Messages.INDENTATION + "OOPS!!! The description of a find command cannot be empty.";
            break;
        }
        return error;
    }

    /**
     * Informs the user that they have entered an unidentifiable command.
     *
     * @return Error message to the user.
     */
    public static String inputUnknown() {
       return Messages.INDENTATION + Messages.UNKNOWN_INPUT_MESSAGE;
    }

    /**
     * Informs the user that they have not entered a valid date.
     *
     * @return Error message to the user.
     */
    public static String dateMissing() {
        return Messages.INDENTATION + Messages.MISSING_DATE;
    }

    /**
     * Informs the user that list has been printed.
     */
    public static void printList() {
        System.out.println(Messages.INDENTATION + Messages.PRINT_LIST_MESSAGE);
    }

    /**
     * Informs the user the number of tasks on their list.
     * @param i Number of tasks.
     */
    public static void printNoOfTasks(int i) {
        System.out.printf(Messages.INDENTATION + Messages.NUMBER_OF_TASKS_MESSAGE, i);
    }

    /**
     * Informs the user that the task has been marked as done.
     */
    public static void markAsDone() {
        System.out.println(Messages.INDENTATION + Messages.MARK_TASK_AS_DONE_MESSAGE);
    }

    /**
     * Informs the user that the task has been added.
     */
    public static void addTask() {
        System.out.println(Messages.INDENTATION + Messages.ADD_TASK);
    }

    /**
     * Informs the user that the task has been deleted.
     */
    public static void deleteTask() {
        System.out.println(Messages.INDENTATION + Messages.DELETE_TASK);
    }

    /**
     * Prints an empty list message.
     */
    public static void printEmptyList() {
        System.out.println(Messages.INDENTATION + Messages.EMPTY_LIST);
    }

    /**
     * Prints tasks found message.
     */
    public static void printFoundTasks() {
        System.out.println(Messages.INDENTATION + Messages.MATCHING_TASK_FOUND);
    }

     /**
      *  Prints no tasks found message.
      */
    public static void noSuchTasksFound() {
        System.out.println(Messages.INDENTATION + Messages.NO_MATCHING_TASK_FOUND);
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