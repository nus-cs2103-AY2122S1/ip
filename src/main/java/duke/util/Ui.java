package duke.util;

import duke.exception.DukeException;

import java.util.Scanner;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 *
 * Description:
 * Encapsulates the UI that interacts with the user through scanner the inputted text from the user and prints
 * messages based on the result of the different Command methods
 *
 * @author Keith Tan
 */
public class Ui {

    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static Scanner commandScanner;

    public Ui() {
        this.commandScanner = new Scanner(System.in);
    }

    /**
     * Reads the next command inputted by the user
     *
     */
    public String readCommand() {
        return commandScanner.nextLine();
    }

    /**
     * Prints out message according to desired format to user
     *
     * @param message message to bve printed
     */
    public void printMessage(String message) {

        String finalMessage = HORIZONTAL_LINE
                + "\n" + message + "\n"
                + HORIZONTAL_LINE;
        System.out.println(finalMessage);
    }

    /**
     * Prints out greeting message
     *
     */
    public void printWelcomeMessage() {
        String greeting = "Hello! I'm Duke"
                + "\nWhat can I do for you?";
        printMessage(greeting);
    }

    /**
     * Prints out goodbye message
     *
     */
    public void printGoodByeMessage() {
        String byeString = "Bye. Hope to see you again soon!";
        printMessage(byeString);
    }

    /**
     * Prints out help message
     *
     */
    public void printHelpMessage() {
        String helpString = "Here are the following commands Duke.util.Duke accepts:\n"
                + "todo - adds a todo task, type 'todo' followed by a description\n"
                + "event - adds a event task, type 'todo' followed by a description and event duration\n"
                + "deadline - adds a deadline task, type 'deadline' followed by a description and event due date\n"
                + "list - shows the current task list\n"
                + "done - marks a task in the task list as complete, type 'done' followed by an integer\n"
                + "delete - deletes a task in the task list, type 'delete' followed by an integer\n"
                + "filter - filter task lists for tasks through searching for a keyword\n"
                + "bye - exits the duke chat bot and saves all task in the task list to the hard disk";
        printMessage(helpString);
    }

    /**
     * Prints out error message
     *
     * @param de DukeException that was thrown during running of the application
     */
    public void printErrorMessage(DukeException de) {
        printMessage(de.toString());
    }

}
