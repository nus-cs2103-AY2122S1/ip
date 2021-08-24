package duke;

import duke.exception.WrongCommandFormatException;
import duke.tasktype.Deadline;
import duke.tasktype.Event;
import duke.tasktype.Task;
import duke.tasktype.Todo;

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

    public static void invalidIndexMessage() {
        System.out.println("Invalid index, please try again");
    }

    public static void formatExceptionMessage(WrongCommandFormatException e) {
        System.out.println(e.getMessage());
    }

    public static void formatUpdatedMessage() {
        System.out.println(
                "Date format has been updated to: "
                        + Duke.getFormat()
        );
    }

    public static void unacceptableFormatMessage() {
        System.out.println("Not an acceptable format. Please try again");
    }

    public static void currentDateFormatMessage() {
        System.out.println("Current format " + Duke.getFormat());
    }

    public static void noSpecificCmdMessage() {
        System.out.println("No specific command specified. Please try again");
    }

    public static void botShutdownMessage() {
        System.out.println("Good riddance! Time to continue my beauty sleep :)");
    }

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

    public static void listAllMessage(MyList list) {
        int listLength = list.getListSize();
        if (listLength == 0) {
            System.out.println("Your list is empty.");
        } else {
            System.out.println("Your list items:");
            for (int i = 0; i < listLength; i ++) {
                Task t = list.getTask(i);
                System.out.printf("%d. %s \n", i + 1, t.toString());
            }
        }
    }

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

    public static void taskAlrCompleted(Task t) {
        System.out.println("`" + t.getDescription().substring(1) + "`" + " is already completed.");
    }

    public static void markCompleteEvent(Event e) {
        System.out.println("Completed: "
                + e.getDescription()
                + " (by:"
                + e.getTimeframe()
                + ")"
        );
        System.out.println("WEW that's another task completed");
    }

    public static void markCompleteTodo(Todo t) {
        System.out.println(
                "Finally! Took you long enough to complete:" + t.getDescription()
        );
    }

    public static void markCompleteDeadline(Deadline d) {
        System.out.println("Completed:"
                + d.getDescription()
                + " (by:"
                + d.getDeadline().format(d.getCurrentFormat()) + ")"
        );
        System.out.println("You didn't overshoot the deadline right?");
    }
}
