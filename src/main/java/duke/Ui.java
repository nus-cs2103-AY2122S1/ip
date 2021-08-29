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
    public void welcomeMessage() {
        System.out.println("Welcome to Ben's. How may I help you?");
    }

    /**
     * Prints out the tasks of the list from the saved file upon starting
     * Duke.
     *
     * @param currList the contents of the file processed into the scanner.
     */
    public static void printTasksOnLoad(Scanner currList) {
        System.out.println("Current list:");
        int counter = 1;
        while (currList.hasNext()) {
            System.out.println(counter + ". " + currList.nextLine());
            counter++;
        }
    }

    /**
     * Prints the message when user starts up Duke for the first time
     * (where no file has been created and no contents saved)
     */
    public static void firstTimeMessage() {
        System.out.println("\nNote: This is your first time using Duke. " +
                "We will now create a new file for you.");
    }

    /**
     * Displays vital information for what the user should input into Duke.
     * Displayed upon starting Duke or when user input fails.
     */
    public static void importantMessage() {
        System.out.println("\nImportant notes: \n1) For deadlines, it must contain the word 'by'" +
                " and events must contain the word 'at'. ");
        System.out.println("2) Deadlines and events must also contain the date and time as such:\n" +
                "after by or at, in the form DATE TIME where the DATE must follow this format:" +
                "YYYY-MM-DD;\n   and TIME must follow the 24hr format (e.g. 2pm = 1400)\n");
    }

    /**
     * Display message for an empty list.
     */
    public static void emptyListMessage() {
        System.out.println("    ***\n" + "    There is currently nothing in the list. \n" +
                "    ***\n");
    }

    /**
     * Prints out the current tasks of the list when using Duke.
     *
     * @param contents the Tasks in the TaskList in Duke.
     */
    public static void getCurrentTasks(ArrayList<Task> contents) {
        int counter = 1;
        System.out.println("    ***\n" + "    These are your tasks in the list:");
        for (Task x: contents) {
            if (x.wasSaved) {
                System.out.println("      " + counter + ". " + x);
            } else {
                System.out.println("      " + counter + ". " + x.getStatusIcon() + " " +
                        x.getDescription());
                counter++;
            }
        }
        System.out.println("    ***\n");
    }

    /**
     * Displays the message when user marks a task as completed.
     *
     * @param task the task that was completed by the user
     */
    public static void markedTask(Task task) {
        System.out.println("    ***\n" + "    You have successfully done this task:\n" +
                "      " + task.getStatusIcon() + " " + task.getDescription() + "\n    ***\n");
    }

    /**
     * Displays the message when user adds a task. English slightly differs when list only
     * has 1 task.
     *
     * @param task the task the user is adding
     * @param count the size of the TaskList currently
     */
    public static void addedTask(Task task, int count) {
        if (count == 1) {
            System.out.println("    ***\n" + "    Understood. Added the task:\n" + "      " +
                    task.printTask() + "\n    You now have " + count + " task in the list.\n" +
                    "    ***\n");
        } else {
            System.out.println("    ***\n" + "    Understood. Added the task:\n" + "      " +
                    task.printTask() + "\n    You now have " + count + " tasks in the list.\n" +
                    "    ***\n");
        }
    }

    /**
     * Prints a message when a user removes a task from the list.
     *
     * @param task the task removed by the user
     */
    public static void removedTask(Task task) {
        System.out.println("    ***\n" + "    You have successfully removed this task:\n" +
                "      " + task.getStatusIcon() + " " + task.getDescription() + "\n    ***\n");
    }

    /**
     * Displays an error message when user input is invalid.
     */
    public static void invalidTask() {
        System.out.println("Invalid input. \nYou may only use the following inputs: " +
                "(bye, list, done, deadline, event, todo) and any text thereafter.");
        importantMessage();
    }

    /**
     * Prints out a list of Tasks matching the user's input when searching for a keyword
     * or characters.
     *
     * @param matchingContents the list of contents that contain the keywords
     *                         requested by the user.
     */
    public static void searchList(ArrayList<Task> matchingContents) {
        int counter = 1;
        System.out.println("    ***\n" + "    Here are the matching tasks in your list:");
        for (Task x: matchingContents) {
            System.out.println("      " + counter + ". " + x.printTask());
        }
        System.out.println("    ***\n");
    }

    /**
     * Displays the message when user is unable to find a matching word in the
     * TaskList when searching.
     */
    public static void searchFoundNothing() {
        System.out.println("No task in the list matches your current search.");
    }

    /**
     * Displays a message indicating that contents have been written safely and correctly
     * to the designated file.
     */
    public static void successfulWriteFile() {
        System.out.println("Successfully written contents to file.");
    }

    /**
     * Displays a message indicating that the file was unable to be written.
     *
     * @param error the error thrown when file is failed to be written to.
     */
    public static void failedWriteFile(String error) {
        System.out.println("Failed to write to the file. Error: " + error);
    }

    /**
     * The goodbye message displayed after user exits Duke (i.e. bye).
     */
    public static void goodbyeMessage() {
        System.out.println("\nGoodbye! Have a nice day. :)");
    }
}
