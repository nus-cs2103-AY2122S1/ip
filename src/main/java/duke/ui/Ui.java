package duke.ui;

import java.util.Scanner;

import duke.task.TaskManager;

/**
 * UI of the application.
 */
public class Ui {
    private static final int DEFAULT_INDENTATION_LEVEL = 4;
    private static final String DEFAULT_SEPARATOR = "_";
    private static final int DEFAULT_SEPARATOR_LENGTH = 60;

    private final Scanner scanner = new Scanner(System.in);
    private final String indent;
    private final String separator;

    /**
     * Constructor for a Ui object, with the indent and separator set to default settings.
     */
    public Ui() {
        indent = " ".repeat(DEFAULT_INDENTATION_LEVEL);
        separator = DEFAULT_SEPARATOR.repeat(DEFAULT_SEPARATOR_LENGTH);
    }

    /**
     * Greets the user upon starting the application.
     * @param taskManager the initial <code>TaskManager</code>, used for displaying different messages depending on
     *                    whether there are tasks found
     */
    public void greet(TaskManager taskManager) {
        if (taskManager.getTaskCount() > 0) {
            print("Welcome back! I'm Duke. These are the tasks I recall from your last visit.\n\n" + taskManager);
        } else {
            print("Hello! I'm Duke. What can I do for you?");
        }
    }

    /**
     * Reads and returns the command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a message when the user exits the application.
     */
    public void farewell() {
        scanner.close();
        print("Goodbye. Hope to see you again soon!");
    }

    private String indent(String str) {
        // Indent all lines
        String indentedString = str.replace("\n", "\n" + indent);
        return indent + indentedString;
    }

    private void printSeparator() {
        System.out.println(indent(separator));
    }

    /**
     * Formats and displays the response to the ser.
     */
    public void print(String response) {
        printSeparator();
        System.out.println(indent(response));
        printSeparator();
        System.out.println();
    }
}
