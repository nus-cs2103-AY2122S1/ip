package duke;

import duke.command.ByeCommand;
import duke.command.ListCommand;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;
import duke.command.TodoCommand;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;

import java.util.Scanner;

/**
 * Represents the user interface of the chatbot
 */
public class Ui {

    private static final String LOGO =
            "$$$$$$$\\                                                             $$$$$\\                     $$\\       \n" +
            "$$  __$$\\                                                            \\__$$ |                    $$ |      \n" +
            "$$ |  $$ | $$$$$$\\   $$$$$$\\   $$$$$$\\   $$$$$$\\   $$$$$$\\              $$ | $$$$$$\\   $$$$$$$\\ $$ |  $$\\ \n" +
            "$$$$$$$  |$$  __$$\\ $$  __$$\\ $$  __$$\\ $$  __$$\\ $$  __$$\\             $$ | \\____$$\\ $$  _____|$$ | $$  |\n" +
            "$$  ____/ $$$$$$$$ |$$ /  $$ |$$ /  $$ |$$$$$$$$ |$$ |  \\__|      $$\\   $$ | $$$$$$$ |$$ /      $$$$$$  / \n" +
            "$$ |      $$   ____|$$ |  $$ |$$ |  $$ |$$   ____|$$ |            $$ |  $$ |$$  __$$ |$$ |      $$  _$$<  \n" +
            "$$ |      \\$$$$$$$\\ $$$$$$$  |$$$$$$$  |\\$$$$$$$\\ $$ |            \\$$$$$$  |\\$$$$$$$ |\\$$$$$$$\\ $$ | \\$$\\ \n" +
            "\\__|       \\_______|$$  ____/ $$  ____/  \\_______|\\__|             \\______/  \\_______| \\_______|\\__|  \\__|\n" +
            "                    $$ |      $$ |                                                                        \n" +
            "                    $$ |      $$ |                                                                        \n" +
            "                    \\__|      \\__|                                                                        ";

    private static final String END_TEXT = "Pepper Jack love Fraggle Rock!";

    private static final String[] LIST_OF_COMMAND_USAGE_TEXT = {
            ByeCommand.USAGE_TEXT,
            ListCommand.USAGE_TEXT,
            DoneCommand.USAGE_TEXT,
            DeleteCommand.USAGE_TEXT,
            TodoCommand.USAGE_TEXT,
            DeadlineCommand.USAGE_TEXT,
            EventCommand.USAGE_TEXT,
    };

    private Scanner sc;

    /** Ui constructor */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads user input.
     *
     * @return User input in string.
     */
    public String readCommand() {
        System.out.print("[YOU] ");
        return sc.nextLine();
    }

    /**
     * Returns a formatted reply with given string.
     *
     * @param str String to format to reply.
     * @return Formatted reply to display to user.
     */
    public static String formatReply(String str) {
        String y_border = "------------------------------------------------------------\n";
        return y_border + "[PEPPER JACK]\n" + str + "\n" + y_border;
    }

    /**
     * Prints reply to display.
     *
     * @param str Reply to display.
     */
    public void reply(String str) {
        System.out.print(formatReply(str));
    }

    /**
     * Prints reply showing tasks to display.
     *
     * @param isAdd If a task was added.
     * @param task String representation of task.
     * @param numOfTasks Number of tasks left.
     */
    public void showTasksReply(boolean isAdd, String task, int numOfTasks) {
        String end = "\nYou have " + numOfTasks +" task(s) now so get off that crack rock!";
        // If adding new task reply with add task message
        if (isAdd) {
            System.out.print(formatReply("Pepper Jack added this task:\n\t" + task + end));
        } else {
            // Else reply with custom message
            System.out.print(formatReply(task + end));
        }
    }

    /** Display welcome message */
    public void showWelcome() {
        System.out.println("The name is\n" + LOGO);
        System.out.print(formatReply("This Pepper Jack, wassup!"));
    }

    /** Display error message */
    public void showLoadingError(String errorMessage) {
        System.out.print(formatReply(errorMessage));
    }

    /** Display help text */
    public void showHelp() {
        String all_help_text = "";
        for (String help_text : LIST_OF_COMMAND_USAGE_TEXT) {
            all_help_text = all_help_text + "\t" + help_text + "\n";
        }
        System.out.print(formatReply("What you sayin brah! You better start makin sense or else...\n"
                + "*Speak in words Pepper Jack can understand*\n" + all_help_text));
    }

    /** Display final message */
    public void showBye() {
        reply(END_TEXT);
    }
}
