package bot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * A class that encapsulates all UI components of Duke.
 */
public class UserInterface {

    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy kkmm");

    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy - hh:mm a");

    private static final String divider = "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n";

    private final Scanner reader;

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
        System.out.println(divider + error + divider);
    }

    /**
     * Shows the error message to the user, specifically when local data is not found.
     */
    public void showLoadingError() {
        System.out.println(divider + "Creating a new storage for the user..." + divider);
    }

    /**
     * Shows the welcome message to the user.
     */
    public void showWelcomeMessage() {
        System.out.println(divider
                + "Hello! My name is Duke!\n\n"
                + " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n"
                + "What can I do for you today?\n"
                + divider);
    }

    /**
     * Shows the exit message to the user.
     */
    public void showExitMessage() {
        System.out.println(divider + "Bye... Hope to see you again soon!\n" + divider);
        Bot.stop();
    }

    /**
     * Shows the message for a task being added, according to the type of task given.
     *
     * @param input  The task to be added to the list.
     * @param type   The type id. Todo: 1, Deadline: 2, Event: 3.
     * @param length The current length of the list.
     * @param time   The information regarding the task (In the format of "(By: ...)" or "(At: ...)", or "" for Todos)
     */
    public static void showTaskAdded(String input, int type, int length, String time) {

        // Determine the string that displays the type of task
        String taskType;
        String prefix;

        switch (type) {
        case 1:
            taskType = "task (Todo)";
            prefix = "[T][ ]";
            break;
        case 2:
            taskType = "task (Deadline)";
            prefix = "[D][ ]";
            break;
        case 3:
            taskType = "task (Event)";
            prefix = "[E][ ]";
            break;
        default:
            taskType = "";
            prefix = "";
        }

        String taskDescription = "Alright. I've added the following " + taskType + ":\n--> " + prefix + " " + input;

        String taskTime =
            type == 2
                ? "(By: " + LocalDateTime.parse(time, inputFormatter).format(outputFormatter) + ")"
                : type == 3
                    ? "(At: " + LocalDateTime.parse(time, inputFormatter).format(outputFormatter) + ")"
                    : "";

        // Return the message accordingly
        System.out.println(divider
                + taskDescription
                + taskTime
                + "\n\n" + "You now have " + (length + 1) + (length == 0 ? " task" : " tasks")
                + " in the list.\n" + divider);
    }
}
