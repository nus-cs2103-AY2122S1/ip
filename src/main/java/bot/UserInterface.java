package main.java.bot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * A class that encapsulates all UI components of Duke.
 */
public class UserInterface {

    private Scanner reader;

    private static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy kkmm");

    private static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy - hh:mm a");

    /**
     * Constructor for the UserInterface class.
     *
     * Takes no parameters.
     */
    UserInterface() {
        this.reader = new Scanner(System.in);
    }

    /**
     * Returns the String scanned from the user input.
     *
     * Takes no parameters.
     *
     * @return A String corresponding to the latest user input.
     */
    public String readCommand() {
        return reader.nextLine();
    }

    /**
     * Shows the error message given to the user.
     *
     * @param error The error message to be shown to the user.
     */
    public void showError(String error) {
        System.out.println("-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                + error
                + "\n-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n");
    }

    /**
     * Shows the error message to the user, specifically when local data is not found.
     */
    public void showLoadingError() {
        System.out.println("-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                + "Creating a new storage for the user..."
                + "\n-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n");
    }

    /**
     * Shows the welcome message to the user.
     */
    public void showWelcomeMessage() {
        System.out.println("-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                + "Hello! My name is Duke!\n\n"
                + " ____        _        \n"
                + "|  _ \\ _   _| |  ____ \n"
                + "| | | | | | | |_/ _  \\\n"
                + "| |_| | |_| |  <   __/\n"
                + "|____/ \\____|_|\\_\\___|\n\n"
                + "What can I do for you today?\n"
                + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n");
    }

    /**
     * Shows the exit message to the user.
     */
    public void showExitMessage() {
        System.out.println("-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                + "Bye... Hope to see you again soon!\n"
                + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n");
        Bot.stop();
    }

    /**
     * Shows the message for a task being added, according to the type of task given.
     *
     * @param input The task to be added to the list.
     * @param type  The type id. Todo: 1, Deadline: 2, Event: 3.
     * @param length The current length of the list.
     * @param time  The information regarding the task (In the format of "(By: ...)" or "(At: ...)", or "" for Todos)
     */
    public static void showTaskAdded(String input, int type, int length, String time) {

        // Determine the string that displays the type of main.java.task
        String taskType;
        String prefix;

        switch (type) {
            case 1:
                taskType = "main.java.task (Todo)";
                prefix = "[T][ ]";
                break;
            case 2:
                taskType = "main.java.task (Deadline)";
                prefix = "[D][ ]";
                break;
            case 3:
                taskType = "main.java.task (Event)";
                prefix = "[E][ ]";
                break;
            default:
                taskType = "";
                prefix = "";
        }

        // Return the message accordingly
        System.out.println("-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                + "Alright. I've added the following "
                + taskType
                + ":\n--> "
                + prefix
                + " "
                + input
                + (type == 2
                ? "(By: " + LocalDateTime.parse(time, inputFormatter).format(outputFormatter) + ")"
                : type == 3
                ? "(At: " + LocalDateTime.parse(time, inputFormatter).format(outputFormatter) + ")"
                : "")
                + "\n\n"
                + "You now have "
                + (length + 1)
                + (length == 0 ? "main/java/task" : " tasks")
                + " in the list.\n"
                + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n");
    }
}
