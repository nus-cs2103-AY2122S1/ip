package aoi.ui;

import aoi.data.TaskList;
import aoi.task.Task;

/**
 * Encapsulates the Ui component of aoi.Aoi.
 *
 * @author Owen Tan
 * @version aoi.Aoi Level-9
 */
public class Ui {
    /**
     * Returns a greeting message.
     *
     * @return A String representation of a Greeting message.
     */
    public static String showGreeting() {
        String message = "MY BEST FRIEND! \nWhat can I do for you?";
        return message;
    }

    /**
     * Returns a farewell message.
     *
     * @return A String representation of a Farewell message.
     */
    public static String showFarewellMsg() {
        String message = "GoodBye Brother!";
        return message;
    }

    /**
     * Returns a AddTask Message.
     *
     * @return A String representation of a AddTask message.
     */
    public static String showAddTaskMsg(Task text) {
        String message = "Sure thing! I've added this task:\n  " + text + "\n";
        return message;
    }

    /**
     * Returns a Completed Task message.
     *
     * @return A String representation of a Task completed message.
     */
    public static String showCompleteTaskMsg(Task text) {
        String message = "Nice! I've marked this task as done:\n  " + text + "\n";
        return message;
    }

    /**
     * Returns a Help message.
     *
     * @return A String representation of a Help message.
     */
    public static String showHelpMsg() {
        String message = "For help, kindly visit the user guide:";
        return message;
    }

    /**
     * Returns a Deleted Task message.
     *
     * @return A String representation of a Deleted Task message.
     */
    public static String showDeleteTaskMsg(Task text) {
        String message = "Noted! I've removed this task:\n  " + text + "\n";
        return message;
    }

    /**
     * Prints number of tasks in TaskList.
     */
    public static String showListCountMsg(TaskList tasks) {
        String message;
        int len = tasks.getLength();
        if (len == 1) {
            message = String.format("Now you have %d task in the list.", len);
        } else {
            message = String.format("Now you have %d tasks in the list.", len);
        }
        return message;
    }
}
