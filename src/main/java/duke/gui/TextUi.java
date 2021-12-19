package duke.gui;

import duke.exception.DukeInvalidCommandException;
import duke.logic.command.ByeCommand;
import duke.logic.parser.Parser;
import duke.logic.tasks.TaskList;

import java.util.Scanner;

/**
 * Utility to help deals with interactions with the user
 */
public class TextUi {
    private final Scanner sc = new Scanner(System.in);
    private final TaskList tasks = new TaskList();
    /**
     * Greets the user.
     */
    private void sendGreetings() {
        String logo = "\t  ____        _        \n"
                + "\t |  _ \\ _   _| | _____ \n"
                + "\t | | | | | | | |/ / _ \\\n"
                + "\t | |_| | |_| |   <  __/\n"
                + "\t |____/ \\__,_|_|\\_\\___|\n";

        insertSeparateLine();
        System.out.println(logo);
        display("Hello! I'm duke.logic.Duke");
        display("What can I do for you?");
        insertSeparateLine();
    }

    /**
     * Prints out the separation line between elements of the program
     */
    public static void insertSeparateLine() {
        String separateLine =
                "___________________________________________________________________________________";
        System.out.println("\t" + separateLine);
    }

    /**
     * Prints out the formatted version of any string content
     *
     * @param content Content to display.
     */
    public static void display(String content) {
        System.out.println("\t" + " " + content);
    }

    /**
     * Prints out the formatted version of any string content between two horizontal lines
     *
     * @param content Content to display.
     */
    public static void displayWithLines(String content) {
        insertSeparateLine();
        System.out.println("\t" + " " + content);
        insertSeparateLine();
    }

    /**
     * Starts the user interface and begin the program
     */
    public void start() {
        sendGreetings();
        String currentCommand = sc.nextLine().trim();
        Parser parser = new Parser(tasks);

        // a bit of hard-code here
        // find a way to get rid of this while loop condition
        while (!currentCommand.equals("bye")) {
            try {
                displayWithLines(parser.invokeCommand(currentCommand));
            } catch (DukeInvalidCommandException e) {
                displayWithLines(e.getMessage());
            }
            currentCommand = sc.nextLine().trim();
        }
        // can't access the task list from here so null is used
        displayWithLines(new ByeCommand().executeCommand(null));
        sc.close();
    }
}
