package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class deals with interactions with the user by displaying
 * information when user input is processed.
 */
public class Ui {
    private static final String MESSAGE_START = "\n    ***\n";
    private static final String MESSAGE_END = "    ***\n";

    /**
     * Empty constructor for Ui.
     */
    public Ui() {}

    /**
     * Displays a welcome message upon starting Duke.
     *
     * @return A welcome message depending on whether it is the first
     *         time starting Duke.
     */
    public static String welcomeMessage() {
        final String startOfWelcomeMessage = "Welcome to Ben's. How may I help you?\n";
        return startOfWelcomeMessage + Storage.printStartingFileContents() + "\n"
                    + importantMessage();
    }

    /**
     * Displays the tasks of the list from the saved file upon starting
     * Duke.
     *
     * @param currList Contents of the file processed into the scanner.
     * @return The list of tasks currently in the file when user opens Duke.
     */
    public static String displayTasksOnLoad(Scanner currList) {
        String output = "\nCurrent list:\n";
        int counter = 1;
        while (currList.hasNext()) {
            output += counter + ". " + currList.nextLine();
            counter++;
        }
        return output;
    }

    /**
     * Displays a message when user starts up Duke for the first time
     * (where no file has been created and no contents saved).
     *
     * @return Message indicating it is the first time user uses Duke.
     */
    public static String firstTimeMessage() {
        return "\nNote: This is your first time using Duke. "
                + "We will now create a new file for you.";
    }

    /**
     * Displays vital information for what the user should input into Duke,
     * upon starting Duke or when user input fails.
     *
     * @return An important message regarding user input for Deadlines & Events.
     */
    public static String importantMessage() {
        return "\nImportant notes: \n1) For deadlines, it must contain the word 'by'"
                + " and events must contain the word 'at'. \n"
                + "2) Deadlines and events must also contain the date and time as such: "
                + "after by or at, in the form DATE TIME where the DATE must follow this format: "
                + "YYYY-MM-DD; and TIME must follow the 24hr format (e.g. 2pm = 1400)\n";
    }

    /**
     * Display message for an empty list.
     *
     * @return A message displaying an empty list.
     */
    public static String emptyListMessage() {
        return (MESSAGE_START + "    There is currently nothing in the list. \n"
                + MESSAGE_END);
    }

    /**
     * Returns the current tasks of the list when using Duke.
     *
     * @param contents Tasks in the TaskList in Duke.
     * @return A list of tasks currently in Duke.
     */
    public static String getCurrentTasks(ArrayList<Task> contents) {
        int counter = 1;
        String output = MESSAGE_START + "    These are your tasks in the list:\n";
        for (Task x: contents) {
            if (x.isSaved) {
                output += "      " + counter + ". " + x + "\n";
            } else {
                output += "      " + counter + ". " + x.getStatusIcon() + " " + x.getDescription() + "\n";
                counter++;
            }
        }
        output += MESSAGE_END;
        return output;
    }

    /**
     * Displays the message when user marks a task as completed.
     *
     * @param task Task that was completed by the user.
     * @return A message indicating message has been completed.
     */
    public static String markTaskMessage(Task task) {
        return MESSAGE_START + "    You have successfully done this task:\n"
                + "      " + task.getStatusIcon() + " " + task.getDescription() + "\n" + MESSAGE_END;
    }

    /**
     * Displays the message when user adds a task. English slightly differs when list only
     * has 1 task.
     *
     * @param task Task the user is adding.
     * @param count Size of the TaskList currently.
     * @return A message indicating the given task was added.
     */
    public static String addTaskMessage(Task task, int count) {
        if (count == 1) {
            return MESSAGE_START + "    Understood. Added the task:\n" + "      "
                    + task.printTask() + "\n    You now have " + count + " task in the list.\n"
                    + MESSAGE_END;
        } else {
            return MESSAGE_START + "    Understood. Added the task:\n" + "      "
                    + task.printTask() + "\n    You now have " + count + " tasks in the list.\n"
                    + MESSAGE_END;
        }
    }

    /**
     * Returns a message when a user removes a task from the list.
     *
     * @param task Task removed by the user.
     * @return A message indicating the task has been removed from the list.
     */
    public static String removeTaskMessage(Task task) {
        return MESSAGE_START + "    You have successfully removed this task:\n"
                + "      " + task.getStatusIcon() + " " + task.getDescription() + MESSAGE_END;
    }

    /**
     * Displays an error message when user input is invalid.
     *
     * @return A message indicating invalid inputs.
     */
    public static String invalidTaskMessage() {
        return "\nInvalid input. \nYou may only use the following inputs: "
                + "(bye, list, done, deadline, event, todo, find) and any text thereafter.\n"
                + importantMessage();
    }

    /**
     * Returns a list of Tasks matching the user's input when searching for a keyword
     * or characters.
     *
     * @param matchingContents List of contents that contain the keywords
     *                         requested by the user.
     * @return A list of tasks containing the user's requested input.
     */
    public static String searchList(ArrayList<Task> matchingContents) {
        int counter = 1;
        String output = MESSAGE_START + "    Here are the matching tasks in your list:\n";
        for (Task x: matchingContents) {
            output += "      " + counter + ". " + x.printTask() + "\n";
        }
        output += MESSAGE_END;
        return output;
    }

    /**
     * Displays the message when user is unable to find a matching word in the
     * TaskList when searching.
     *
     * @return A message indicating no tasks match the requested search.
     */
    public static String searchFoundNothing() {
        return "\nNo task in the list matches your current search.\n";
    }

    /**
     * Displays a message indicating that contents have been written safely and correctly
     * to the designated file.
     *
     * @return A message indicating successful write to file for Duke's contents.
     */
    public static String successfulWriteFileMessage() {
        return "Successfully written contents to file.\n";
    }

    /**
     * Displays a message indicating that the file was unable to be written.
     *
     * @param error Error thrown when file is failed to be written to.
     * @return Error message indicating failure to write to file.
     */
    public static String failToWriteFileMessage(String error) {
        return "Failed to write to the file. Error: \n" + error;
    }

    /**
     * Displays a goodbye message after user exits Duke (i.e. bye).
     *
     * @return A goodbye message when user exits Duke.
     */
    public static String goodbyeMessage() {
        return "\nGoodbye! Have a nice day. :)";
    }

    /**
     * Displays an error message when user inputs a number larger than list count or
     * lower than or equal to 0.
     *
     * @return An error message when user inputs a number unobtainable by the list.
     */
    public static String numberOutsideOfArrayBounds() {
        return "Invalid number - Number is not within list count.";
    }

    /**
     * Displays a error message when user has invalid input for done.
     *
     * @return An error message when user uses wrong format for indicating correct tasks.
     */
    public static String invalidDoneInput() {
        return "Invalid input. Requires a number after done (e.g. done 1).";
    }

    /**
     * Displays a error message when user has invalid input for delete.
     *
     * @return An error message indicating wrong format for deleting tasks.
     */
    public static String invalidDeleteInput() {
        return "Invalid input. Requires a number after delete (e.g. delete 1).";
    }

    /**
     * Displays a error message when user has invalid input for find.
     *
     * @return An error message when user has invalid input for find.
     */
    public static String invalidFindInput() {
        return "Invalid input. Requires a number after find (e.g. find 1).";
    }
}
