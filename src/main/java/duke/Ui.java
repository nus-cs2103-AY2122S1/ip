package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class deals with interactions with the user by displaying
 * information when user input is processed.
 */
public class Ui {
    /**
     * Empty constructor for Ui.
     */
    public Ui() {}

    /**
     * Prints out the welcome message upon starting Duke.
     */
    public static String welcomeMessage() {
        return "Welcome to Ben's. How may I help you?\n" + Storage.printStartingFileContents();
    }

    /**
     * Prints out the tasks of the list from the saved file upon starting
     * Duke.
     *
     * @param currList Contents of the file processed into the scanner.
     */
    public static String printTasksOnLoad(Scanner currList) {
        String output = "\nCurrent list:\n";
        int counter = 1;
        while (currList.hasNext()) {
            output += counter + ". " + currList.nextLine();
            counter++;
        }
        return output;
    }

    /**
     * Prints the message when user starts up Duke for the first time
     * (where no file has been created and no contents saved).
     */
    public static String firstTimeMessage() {
        return "\nNote: This is your first time using Duke. " +
                "We will now create a new file for you.\n";
    }

    /**
     * Displays vital information for what the user should input into Duke.
     * Displayed upon starting Duke or when user input fails.
     */
    public static String importantMessage() {
        return "\nImportant notes: \n1) For deadlines, it must contain the word 'by'" +
                " and events must contain the word 'at'. \n" +
                "2) Deadlines and events must also contain the date and time as such:\n" +
                "after by or at, in the form DATE TIME where the DATE must follow this format:" +
                "YYYY-MM-DD;\n   and TIME must follow the 24hr format (e.g. 2pm = 1400)\n";
    }

    /**
     * Display message for an empty list.
     */
    public static String emptyListMessage() {
       return ("\n    ***\n" + "    There is currently nothing in the list. \n" +
                "    ***\n");
    }

    /**
     * Prints out the current tasks of the list when using Duke.
     *
     * @param contents Tasks in the TaskList in Duke.
     */
    public static String getCurrentTasks(ArrayList<Task> contents) {
        int counter = 1;
        String output = "\n    ***\n" + "    These are your tasks in the list:\n";
        for (Task x: contents) {
            if (x.wasSaved) {
                output += "      " + counter + ". " + x + "\n";
            } else {
                output += "      " + counter + ". " + x.getStatusIcon() + " " + x.getDescription() + "\n";
                counter++;
            }
        }
        output += "    ***\n";
        return output;
    }

    /**
     * Displays the message when user marks a task as completed.
     *
     * @param task Task that was completed by the user.
     */
    public static String markTaskMessage(Task task) {
        return "\n    ***\n" + "    You have successfully done this task:\n" +
                "      " + task.getStatusIcon() + " " + task.getDescription() + "\n    ***\n";
    }

    /**
     * Displays the message when user adds a task. English slightly differs when list only
     * has 1 task.
     *
     * @param task Task the user is adding.
     * @param count Size of the TaskList currently.
     */
    public static String addTaskMessage(Task task, int count) {
        if (count == 1) {
            return "\n    ***\n" + "    Understood. Added the task:\n" + "      " +
                    task.printTask() + "\n    You now have " + count + " task in the list.\n" +
                    "    ***\n";
        } else {
            return "\n    ***\n" + "    Understood. Added the task:\n" + "      " +
                    task.printTask() + "\n    You now have " + count + " tasks in the list.\n" +
                    "    ***\n";
        }
    }

    /**
     * Prints a message when a user removes a task from the list.
     *
     * @param task Task removed by the user.
     */
    public static String removeTaskMessage(Task task) {
        return "\n    ***\n" + "    You have successfully removed this task:\n" +
                "      " + task.getStatusIcon() + " " + task.getDescription() + "\n    ***\n";
    }

    /**
     * Displays an error message when user input is invalid.
     */
    public static String invalidTaskMessage() {
        return "\nInvalid input. \nYou may only use the following inputs: " +
                "(bye, list, done, deadline, event, todo) and any text thereafter.\n"
                + importantMessage();
    }

    /**
     * Prints out a list of Tasks matching the user's input when searching for a keyword
     * or characters.
     *
     * @param matchingContents List of contents that contain the keywords
     *                         requested by the user.
     */
    public static String searchList(ArrayList<Task> matchingContents) {
        int counter = 1;
        String output = "\n    ***\n" + "    Here are the matching tasks in your list:\n";
        for (Task x: matchingContents) {
            output += "      " + counter + ". " + x.printTask() + "\n";
        }
        output += "    ***\n";
        return output;
    }

    /**
     * Displays the message when user is unable to find a matching word in the
     * TaskList when searching.
     */
    public static String searchFoundNothing() {
        return "\nNo task in the list matches your current search.\n";
    }

    /**
     * Displays a message indicating that contents have been written safely and correctly
     * to the designated file.
     */
    public static String successfulWriteFileMessage() {
        return "Successfully written contents to file.\n";
    }

    /**
     * Displays a message indicating that the file was unable to be written.
     *
     * @param error Error thrown when file is failed to be written to.
     */
    public static String failToWriteFileMessage(String error) {
        return "Failed to write to the file. Error: \n" + error;
    }

    /**
     * Displays a goodbye message after user exits Duke (i.e. bye).
     */
    public static String goodbyeMessage() {
        return "\nGoodbye! Have a nice day. :)";
    }
}
