package duke;

import duke.exception.WrongCommandFormatException;
import duke.tasktype.Deadline;
import duke.tasktype.Event;
import duke.tasktype.Task;
import duke.tasktype.Todo;


/**
 * Class that handles the user interface.
 *
 * @author Houten Teo
 * @version CS2103T week 3
 */
public class Ui {

    /**
     * Returns the welcome message.
     * @return The welcome message.
     */
    public static String getWelcomeMessage() {
        System.out.println(
                "Yo! Duke here \n"
                        + "What did you call me for? \n"
                        + "It better be something useful or else... \n"
        );
        return "Yo! Duke here \n"
                + "What did you call me for? \n"
                + "It better be something useful or else... \n";
    }

    /**
     * Prints out the error message when loading of tasks fails
     */
    public static void getLoadingErrorMessage() {
        System.out.println("Error loading duke.tasks");
    }

    /**
     * Prints out the error message when user keys in an invalid index.
     * @return the error message.
     */
    public static String getInvalidIndexMessage() {
        System.out.println("Invalid index, please try again");
        return "Invalid index, please try again";
    }

    /**
     * Prints out the message from the wrongCommandFormatException.
     * @param e the exception to get the message from.
     * @return the error message.
     */
    public static String getFormatExceptionMessage(WrongCommandFormatException e) {
        System.out.println(e.getMessage());
        return e.getMessage();
    }

    /**
     * Prints out the message when the user updates the date format.
     * @return The message.
     */
    public static String getFormatUpdatedMessage() {
        System.out.println("Date format has been updated to: " + Duke.getFormat());
        return "Date format has been updated to: " + Duke.getFormat();
    }

    /**
     * Prints and returns the message when the user tries to update into and invalid date format.
     * @return the message.
     */
    public static String getUnacceptableFormatMessage() {
        System.out.println("Not an acceptable format. Please try again");
        return "Not an acceptable format. Please try again";
    }

    /**
     * Prints and returns the current date format.
     * @return the message.
     */
    public static String getCurrentDateFormatMessage() {
        System.out.println("Current format " + Duke.getFormat());
        return "Current format " + Duke.getFormat();
    }

    /**
     * Prints and returns the message when the user inputs an unrecognised command.
     * @return The message.
     */
    public static String getNoFormatSpecifiedMessage() {
        System.out.println("No format specified. Please try again.");
        return "No format specified. Please try again.";
    }

    /**
     * Returns the message when there is an unrecognised command.
     * @return the message.
     */
    public static String getUnrecognisedCmdMessage() {
        System.out.println("Unrecognised command. Please try again");
        return "Unrecognised command. Please try again";
    }

    /**
     * Prints out the goodbye message.
     * @return the message.
     */
    public static String getBotShutdownMessage() {
        System.out.println("Good riddance! Time to continue my beauty sleep :)");
        return "Good riddance! Time to continue my beauty sleep :)";
    }

    /**
     * Informs the user what task has been added and the number of items in the list.
     * @param t the task that the user added.
     * @param list the list that the task is added to.
     * @return the message.
     */
    public static String getAddTaskMessage(Task t, MyList list) {
        int noOfItems = list.getListSize();
        String s = "Got it! I have added: \n"
                + t.toString()
                + "\n";
        if (noOfItems == 1) {
            s += "You now have 1 item in your list \n";
        } else {
            s += "You now have " + noOfItems + " items in your list \n";
        }
        System.out.println(s);
        return s;
    }

    /**
     * Prints out all the items in the list.
     * @param list the list that contains the items to be printed out.
     * @return the message.
     */
    public static String getListAllMessage(MyList list) {
        int listLength = list.getListSize();
        String output = "";
        if (listLength == 0) {
            output = "Your list is empty.";
        } else {
            output = "Your list items: \n";
            for (int i = 0; i < listLength; i++) {
                Task t = list.getTask(i);
                output += i + 1 + ". " + t.toString() + "\n";
            }
        }
        System.out.println(output);
        return output;
    }

    /**
     * Informs the user what task has been deleted.
     * @param t The task that has been deleted.
     * @param list The list that the task has been deleted from.
     * @return the message.
     */
    public static String getDeleteTaskMessage(Task t, MyList list) {
        int noOfItems = list.getListSize();
        String s = "Noted. I've removed this task: \n"
                + t.toString()
                + "\n";
        if (noOfItems == 1) {
            s += "You now have 1 item in your list \n";
        } else {
            s += "You now have " + noOfItems + " items in your list \n";
        }
        System.out.println(s);
        return s;
    }

    /**
     * Prints out the message when the user tries to complete a task
     * that has already been completed.
     * @param t The completed task that the user tries to mark as complete
     * @return the message
     */
    public static String getTaskAlrCompletedMessage(Task t) {
        String s = "`" + t.getDescription().substring(1) + "`" + " is already completed.";
        System.out.println(s);
        return s;
    }

    /**
     * Prints out the message when the user marks an event as completed.
     * @param e The task that was marked as completed.
     * @return the message
     */
    public static String getMarkCompleteEventMessage(Event e) {
        String s = "Completed:"
                + e.getDescription()
                + " (at: "
                + e.getTimeframe()
                + ")\n"
                + "WEW that's another task completed";
        System.out.println(s);
        return s;
    }

    /**
     * Prints out the message when the user marks a to do as completed.
     * @param t The to do that was marked completed.
     * @return the message.
     */
    public static String getMarkCompleteTodoMessage(Todo t) {
        System.out.println("Finally! Took you long enough to complete:" + t.getDescription());
        return "Finally! Took you long enough to complete:" + t.getDescription();
    }

    /**
     * Prints out the message when the user marks a deadline as completed.
     * @param d The deadline that was marked as completed.
     * @return the message.
     */
    public static String getMarkCompleteDeadlineMessage(Deadline d) {
        String s = "Completed: "
                + d.getDescription()
                + " (by: "
                + d.getDeadline().format(d.getCurrentFormat()) + ")\n"
                + "You didn't overshoot the deadline right?";
        System.out.println(s);
        return s;
    }

    /**
     * Prints out the number of matching tasks.
     * @param counter The number of matching tasks.
     * @return the message.
     */
    public static String getContainsKeywordMessage(int counter) {

        if (counter > 0) {
            if (counter == 1) {
                System.out.println("There is 1 matching task in your list: \n");
                return "There is 1 matching task in your list: \n";
            } else {
                System.out.println("There are " + counter + " matching tasks in your list: \n");
                return "There are " + counter + " matching tasks in your list: \n";
            }
        } else {
            System.out.println("There are no matching tasks in your list\n");
            return "There are no matching tasks in your list\n";
        }
    }

    /**
     * Prints out the string representations of all the matching tasks.
     * @param matchingList The array containing the matching tasks.
     * @return the message.
     */
    public static String getTaskWithKeywordMessage(Task[] matchingList) {
        int noOfItems = matchingList.length;
        String s = "";
        for (int i = 0; i < noOfItems; i++) {
            if (matchingList[i] != null) {
                int index = i + 1;
                Task matchingTask = matchingList[i];
                s += index + ". " + matchingTask.toString() + "\n";
            }
        }
        System.out.println(s);
        return s;
    }

    /**
     * Informs the user that he has not input a keyword after the find command.
     * @return the message.
     */
    public static String getNoKeywordSpecifiedMessage() {
        System.out.println("No keyword specified. Please try again");
        return "No keyword specified. Please try again";
    }

    /**
     * Informs the user that the number of days specified for the 'remind'
     * command is not accepted.
     * @return The message.
     */
    public static String getNoDaySpecifiedMessage() {
        System.out.println("No proper timeframe specified. Please try again");
        return "No proper timeframe specified. Please try again";
    }

    /**
     * Appends the number of days from today to the end of the deadline.
     * @param d The deadline.
     * @param daysWithin the number of days between today and the deadline.
     * @return The String representation of deadline including the days between.
     */
    public static String getWithinDeadlineMessage(Deadline d, long daysWithin, int index) {
        if (daysWithin < 0) {
            long overdue = Math.abs(daysWithin);
            return index + ". " + d.toString() + " (overdue by " + overdue + " days)\n";
        } else if (daysWithin == 1) {
            return index + ". " + d.toString() + " (" + daysWithin + " day)\n";
        } else {
            return index + ". " + d.toString() + " (" + daysWithin + " days)\n";
        }
    }

    /**
     * Returns the header message for duke's response to the 'remind' command.
     * @param noOfDeadlines The number of deadlines in the displayed list.
     * @param days The number of days specified by the user.
     * @return The header message.
     */
    public static String getRemindHeader(int noOfDeadlines, int days) {
        if (noOfDeadlines == 0) {
            return "You have no upcoming deadlines within " + days + " days\n";
        } else if (days == 1) {
            return "These are the deadlines due within " + days + " day\n";
        } else {
            return "These are the deadlines due within " + days + " days\n";
        }
    }

    /**
     * Returns Duke's reply when the user inputs the clear command.
     * @return Duke's reply
     */
    public static String getClearMessage() {
        return "Your list has been cleared.";
    }

    public static String getHelpMessage() {
        return "Here are the commands duke supports:\n"
                + "1. todo {description}\n"
                + "2. deadline {description} /by {date}\n"
                + "3. event {description} /at {date/location}\n"
                + "4. delete {Index}\n"
                + "5. list\n"
                + "6. find {Index}\n"
                + "7. remind {days}\n"
                + "8. format\n"
                + "9. setFormat {new format}\n"
                + "10. clear\n"
                + "11. bye\n"
                + "12. help\n"
                + "Refer to the user guide for more information";

    }
}
