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
        return sc.nextLine();
    }

    /**
     * Returns a formatted reply with given string.
     *
     * @param str String to format to reply.
     * @return Formatted reply to display to user.
     */
    public static String formatReply(String str) {
        return str + "\n";
    }

    /**
     * Prints reply to display.
     *
     * @param str Reply to display.
     */
    public String reply(String str) {
        String reply = formatReply(str);
        return reply;
    }

    /**
     * Prints reply showing tasks to display.
     *
     * @param isAdd If a task was added.
     * @param task String representation of task.
     * @param numOfTasks Number of tasks left.
     */
    public String showTasksReply(boolean isAdd, String task, int numOfTasks) {
        String end = "\nYou have " + numOfTasks +" task(s) now so get off that crack rock!";

        if (isAdd) {
            return formatReply("Pepper Jack added this task:\n\t" + task + end);
        } else {
            return formatReply(task + end);
        }
    }

    /** Display welcome message */
    public static String showWelcome() {
        return formatReply("This Pepper Jack, wassup!");
    }

    /** Display error message */
    public static String showLoadingError(String errorMessage) {
        return formatReply(errorMessage);
    }

    /** Display help text */
    public String showHelp() {
        String all_help_text = "";
        for (String help_text : LIST_OF_COMMAND_USAGE_TEXT) {
            all_help_text = all_help_text + "\t" + help_text + "\n";
        }

        return formatReply("What you sayin brah! You better start makin sense or else...\n"
                + "*Speak in words Pepper Jack can understand*\n" + all_help_text);
    }

    /** Display final message */
    public String showBye() {
        return reply(END_TEXT);
    }
}
