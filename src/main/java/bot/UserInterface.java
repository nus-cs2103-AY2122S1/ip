package main.java.bot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UserInterface {

    private Scanner reader;

    private static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy kkmm");

    private static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy - hh:mm a");

    UserInterface() {
        this.reader = new Scanner(System.in);
    }

    public String readCommand() {
        return reader.nextLine();
    }

    public void showError(String error) {
        System.out.println("-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                + error
                + "\n-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n");
    }

    public void showLoadingError() {
        System.out.println("-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                + "Creating a new storage for the user..."
                + "\n-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n");
    }

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

    public void showExitMessage() {
        System.out.println("-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                + "Bye... Hope to see you again soon!\n"
                + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n");
        Bot.stop();
    }

    /**
     * Shows the message for a main.java.task being added, according to the type of the main.java.task given.
     *
     * @param input The main.java.task to be added to the list.
     * @param type  The type id. Todo: 1, Deadline: 2, Event: 3.
     * @param length The current length of the list.
     * @param time  The information regarding the main.java.task (In the format of "(By: ...)" or "(At: ...)", or "" for Todos)
     * @return The String sequence showing that the main.java.task has been added to the list.
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
