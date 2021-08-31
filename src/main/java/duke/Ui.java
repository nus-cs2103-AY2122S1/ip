package duke;

import duke.tasktype.Task;
import duke.tasktype.Deadline;
import duke.tasktype.Event;
import duke.tasktype.Todo;
import duke.exception.WrongCommandFormatException;

/**
 * Class that handles the user interface.
 *
 * @author Houten Teo
 * @version CS2103T week 3
 */
public class Ui {

    /**
     * Method to print out the welcome message when the bot starts.
     */
    public static void welcomeMessage() {
        System.out.println(
                "Yo! duke.Duke here \n"
                        + "What did you call me for? \n"
                        + "It better be something useful or else... \n"
        );
    }

    /**
     * Method to print out the error message when loading of tasks fails
     */
    public static void loadingError() {
        System.out.println("Error loading duke.tasks");
    }

    /**
     * Method to print out the error message when user keys in an invalid index.
     */
    public static String invalidIndexMessage() {
        return "Invalid index, please try again";
    }

    /**
     * Method to print out the message from the wrongCommandFormatException.
     * @param e the exception to get the message from.
     */
    public static String formatExceptionMessage(WrongCommandFormatException e) {
        return e.getMessage();
    }

    /**
     * Method to print out the message when the user updates the date format.
     */
    public static String formatUpdatedMessage() {
        return "Date format has been updated to: " + Duke.getFormat();
    }

    /**
     * Method to print out the message when the user tries to update into and invalid date format.
     */
    public static String unacceptableFormatMessage() {
        System.out.println("Not an acceptable format. Please try again");
        return "Not an acceptable format. Please try again";
    }

    /**
     * Method to print out the current date format.
     */
    public static String currentDateFormatMessage() {
        return "Current format " + Duke.getFormat();
    }

    public static String noFormatSpecifiedMessage() {
        return "No format specified. Please try again.";
    }

    /**
     * Method when the user keys in and unrecognisable command.
     */
    public static String noSpecificCmdMessage() {
        return "No specific command specified. Please try again";
    }

    /**
     * Method to print out the goodbye message.
     */
    public static String botShutdownMessage() {
        return "Good riddance! Time to continue my beauty sleep :)";
    }

    /**
     * Method to inform the user what taks has been added and the number of items in the list.
     * @param t the task that the user added.
     * @param list the list that the task is added to.
     */
    public static String addTaskMessage(Task t, MyList list) {
        int noOfItems = list.getListSize();
        String s = "Got it! I have added: \n"
                + t.toString()
                + "\n";
        if (noOfItems == 1) {
            s += "You now have 1 item in your list \n";
        } else {
            s += "You now have " + noOfItems + " items in your list \n";
        }
        return s;
    }

    /**
     * Method to print out all the items in the list.
     * @param list the list that contains the items to be printed out.
     */
    public static String listAllMessage(MyList list) {
        int listLength = list.getListSize();
        String output = "";
        if (listLength == 0) {
            output = "Your list is empty.";
        } else {
            output = "Your list items: \n";
            for (int i = 0; i < listLength; i ++) {
                Task t = list.getTask(i);
                output += i + 1 + ". " + t.toString() + "\n";
            }
        }
        return output;
    }

    /**
     * Method to inform the user what task has been deleted.
     * @param t The task that has been deleted.
     * @param list The list that the task has been deleted from.
     */
    public static String deleteTaskMessage(Task t, MyList list) {
        int noOfItems = list.getListSize();
        String s = "Noted. I've removed this task: \n"
                + t.toString()
                + "\n";
        if (noOfItems == 1) {
            s += "You now have 1 item in your list \n";
        } else {
            s += "You now have " + noOfItems + " items in your list \n";
        }
        return s;
    }

    /**
     * Method to print out the message when the user tries to complete a task
     * that has already been completed.
     * @param t The completed task that the user tries to mark as complete
     */
    public static String taskAlrCompleted(Task t) {
        String s = "`" + t.getDescription().substring(1) + "`" + " is already completed.";
        return s;
    }

    /**
     * Method to print out the message when the user marks an event as completed.
     * @param e The task that was marked as completed.
     */
    public static String markCompleteEvent(Event e) {
        String s = "Completed:"
                + e.getDescription()
                + " (by:"
                + e.getTimeframe()
                + ")\n"
                + "WEW that's another task completed";
        return s;
    }

    /**
     * Method to print out the message when the user marks a to do as completed.
     * @param t The to do that was marked completed.
     */
    public static String markCompleteTodo(Todo t) {
        return "Finally! Took you long enough to complete:" + t.getDescription();
    }

    /**
     * Method to print out the message when the user marks a deadline as completed.
     * @param d The deadline that was marked as completed.
     */
    public static String markCompleteDeadline(Deadline d) {
        String s = "Completed: "
                + d.getDescription()
                + " (by:"
                + d.getDeadline().format(d.getCurrentFormat()) + ")\n"
                + "You didn't overshoot the deadline right?";
        return s;
    }

    /**
     * Method to print out the number of matching tasks.
     * @param counter The number of matching tasks.
     */
    public static String containsKeyword(int counter) {

        if (counter > 0) {
            if (counter == 1) {
                return "THere is 1 matching task in your list: \n";
            } else {
                return "THere are " + counter + " matching task in your list: \n";
            }
        } else {
            return "There are no matching tasks in your list\n";
        }
    }

    /**
     * Method to print out the string representations of all the matching tasks.
     * @param matchingList The array containing the matching tasks.
     */
    public static String containsKeywordTask(Task[] matchingList) {
        int noOfItems = matchingList.length;
        String s = "";
        for (int i = 0; i < noOfItems; i++) {
            if (matchingList[i] != null) {
                int index = i + 1;
                Task matchingTask = matchingList[i];
                s += index + ". " + matchingTask.toString() + "\n";
            }
        }
        return s;
    }

    /**
     * Method to inform the user that he has not input a keyword after the find command.
     */
    public static String noKeywordSpecifiedMessage() {
        return "No keyword specified. Please try again";
    }
}
