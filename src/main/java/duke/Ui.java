package duke;

import duke.tasktype.Task;
import duke.tasktype.Deadline;
import duke.tasktype.Event;
import duke.tasktype.Todo;
import duke.exception.WrongCommandFormatException;

import java.util.ArrayList;

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
    public static void invalidIndexMessage() {
        System.out.println("Invalid index, please try again");
    }

    /**
     * Method to print out the message from the wrongCommandFormatException.
     * @param e the exception to get the message from.
     */
    public static void formatExceptionMessage(WrongCommandFormatException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Method to print out the message when the user updates the date format.
     */
    public static void formatUpdatedMessage() {
        System.out.println(
                "Date format has been updated to: "
                        + Duke.getFormat()
        );
    }

    /**
     * Method to print out the message when the user tries to update into and invalid date format.
     */
    public static void unacceptableFormatMessage() {
        System.out.println("Not an acceptable format. Please try again");
    }

    /**
     * Method to print out the current date format.
     */
    public static void currentDateFormatMessage() {
        System.out.println("Current format " + Duke.getFormat());
    }

    public static void noFormatSpecifiedMessage() {
        System.out.println("No format specified. Please try again.");
    }

    /**
     * Method when the user keys in and unrecognisable command.
     */
    public static void noSpecificCmdMessage() {
        System.out.println("No specific command specified. Please try again");
    }

    /**
     * Method to print out the goodbye message.
     */
    public static void botShutdownMessage() {
        System.out.println("Good riddance! Time to continue my beauty sleep :)");
    }

    /**
     * Method to inform the user what taks has been added and the number of items in the list.
     * @param t the task that the user added.
     * @param list the list that the task is added to.
     */
    public static void addTaskMessage(Task t, MyList list) {
        System.out.println("Got it! I have added:");
        System.out.println(t.toString());
        int noOfItems = list.getListSize();
        if (noOfItems == 1) {
            System.out.printf("You now have %d item in your list \n", noOfItems);
        } else {
            System.out.printf("You now have %d items in your list \n", noOfItems);
        }
    }

    /**
     * Method to print out all the items in the list.
     * @param list the list that contains the items to be printed out.
     */
//    public static void listAllMessage(MyList list) {
//        int listLength = list.getListSize();
//        if (listLength == 0) {
//            System.out.println("Your list is empty.");
//        } else {
//            System.out.println("Your list items:");
//            for (int i = 0; i < listLength; i ++) {
//                Task t = list.getTask(i);
//                System.out.printf("%d. %s \n", i + 1, t.toString());
//            }
//        }
//    }
    public static String listAllMessage(MyList list) {
        int listLength = list.getListSize();
        String output = "";
        if (listLength == 0) {
            output = "Your list is empty.";
        } else {
            output = "Your list items: \n";
            for (int i = 0; i < listLength; i ++) {
                Task t = list.getTask(i);
                output += i + 1 + ". " + t.toString();
            }
        }
        return output;
    }

    /**
     * Method to inform the user what task has been deleted.
     * @param t The task that has been deleted.
     * @param list The list that the task has been deleted from.
     */
    public static void deleteTaskMessage(Task t, MyList list) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t.toString());
        int noOfItems = list.getListSize();
        if (noOfItems == 1) {
            System.out.printf("You now have %d item in your list \n", noOfItems);
        } else {
            System.out.printf("You now have %d items in your list \n", noOfItems);
        }
    }

    /**
     * Method to print out the message when the user tries to complete a task
     * that has already been completed.
     * @param t The completed task that the user tries to mark as complete
     */
    public static void taskAlrCompleted(Task t) {
        System.out.println("`" + t.getDescription().substring(1) + "`" + " is already completed.");
    }

    /**
     * Method to print out the message when the user marks an event as completed.
     * @param e The task that was marked as completed.
     */
    public static void markCompleteEvent(Event e) {
        System.out.println("Completed: "
                + e.getDescription()
                + " (by:"
                + e.getTimeframe()
                + ")"
        );
        System.out.println("WEW that's another task completed");
    }

    /**
     * Method to print out the message when the user marks a to do as completed.
     * @param t The to do that was marked completed.
     */
    public static void markCompleteTodo(Todo t) {
        System.out.println(
                "Finally! Took you long enough to complete:" + t.getDescription()
        );
    }

    /**
     * Method to print out the message when the user marks a deadline as completed.
     * @param d The deadline that was marked as completed.
     */
    public static void markCompleteDeadline(Deadline d) {
        System.out.println("Completed:"
                + d.getDescription()
                + " (by:"
                + d.getDeadline().format(d.getCurrentFormat()) + ")"
        );
        System.out.println("You didn't overshoot the deadline right?");
    }

    /**
     * Method to print out the number of matching tasks.
     * @param counter The number of matching tasks.
     */
    public static void containsKeyword(int counter) {
        if (counter > 0) {
            if (counter == 1) {
                System.out.printf("THere is %d matching task in your list: \n", counter);
            } else {
                System.out.printf("THere are %d matching tasks in your list: \n", counter);
            }
        } else {
            System.out.println("There are no matching tasks in your list");
        }

    }

    /**
     * Method to print out the string representations of all the matching tasks.
     * @param matchingList The array containing the matching tasks.
     */
    public static void containsKeywordTask(Task[] matchingList) {
        int noOfItems = matchingList.length;

        for (int i = 0; i < noOfItems; i++) {
            if (matchingList[i] != null) {
                Task matchingTask = matchingList[i];
                System.out.printf("%d. %s \n", i + 1, matchingTask.toString());
            }
        }

    }

    /**
     * Method to inform the user that he has not input a keyword after the find command.
     */
    public static void noKeywordSpecifiedMessage() {
        System.out.println("No keyword specified. Please try again");
    }
}
