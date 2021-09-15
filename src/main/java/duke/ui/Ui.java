package duke.ui;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the Ui class that handles interaction with user.
 *
 * @author Sherman Ng Wei Sheng
 */
public class Ui {
    private static final String MESSAGE_GREETING =
            "Hello! I'm Duke\n"
            + "What can I do for you?\n"
            + "Type 'help' to see the wonders that I can do!";
    private static final String HORIZONTAL_LINE_WITH_BREAK =
            "        ____________________________________________________________\n";
    private Scanner sc;

    /**
     * Constructor for the Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Closes the scanner object.
     */
    public void closeScanner() {
        sc.close();
    }

    /**
     * Prints and returns the greeting message of the Chatbot.
     *
     * @return The formatted string of the message.
     */
    public String printAndReturnGreetingsString() {
        return printAndReturnMessage(MESSAGE_GREETING);
    }

    /**
     * Prints and returns the formatted list of content in Task List.
     *
     * @param list The TaskList to be processed and printed.
     * @return The formatted string of the message.
     */
    public String printAndReturnListString(TaskList list) {
        String message = list.generateMessage();
        return printAndReturnMessage(message);
    }

    /**
     * Prints and returns the log for task object being added.
     *
     * @param task The task object to be printed.
     * @param list The task list object that the task is being added to.
     * @return The formatted string of the message.
     */
    public String printAndReturnAddedTaskMessage(Task task, TaskList list) {
        String message =
                "Got it. I've added this task:\n"
                + "  " + task + "\n"
                + String.format("Now you have %d tasks in the list.", list.size());
        return printAndReturnMessage(message);
    }

    /**
     * Prints the input content to console with formatting
     * and returns the content of the message.
     * It is the intended for the message to be both printed and returned.
     *
     * @param content The content to be printed.
     * @return The printed message without the formatting.
     */
    public String printAndReturnMessage(String content) {
        String formatString =
                HORIZONTAL_LINE_WITH_BREAK
                + "        %s\n"
                + HORIZONTAL_LINE_WITH_BREAK;
        System.out.printf(formatString, content.replaceAll("\n", "\n        "));
        return content;
    }
}
