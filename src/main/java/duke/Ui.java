package duke;

import commands.Command;
import tasks.Task;

import java.util.Scanner;

/**
 * Class responsible for interacting with users.
 */
public class Ui {

    /** System messages */
    public static final String DEADLINE_FORMAT = "deadline {Deadline name} -by {Date to be completed by}.\n"
            + "Use \"/by\" to specify that the input date is a formatted date.";
    public static final String EVENT_FORMAT = "event {Event name} -at {Date of event}.\n"
            + "Use \"/at\" to specify that the input date is a formatted date.";
    public static final String TODO_FORMAT = "todo {Todo name}";
    public static final String DATE_FORMAT = "dd/MM/yyyy or dd/MM/yyyy HHmm";

    /**
     * Starts up the chat bot. It prints a welcome message for the user and then waits
     * for a user input. It will then evaluate and execute inputs given by the user until the
     * user ends the chat.
     */
    public void start() {
        showStartMessage();
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser(scanner);
        Storage.initialise(parser.getTaskList());
        while (true) {
            String input = parser.getUserInput();
            Command command = parser.parseUserInput(input);
            command.execute();
            if (command.isExit()) {
                break;
            }
        }
    }

    private static void showStartMessage() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "\n");
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I help you with?\n");
    }

    /**
     * Prints a goodbye message to the user.
     */
    public static void endChat() {
        System.out.println("Bye!! Hope to see you again!!");
    }

    /**
     * Prints an error message when the user inputs the task but in the wrong message. The message
     * tells the user that an error has occurred and how to correctly the input the respective task.
     *
     * @param type The type of task inputted by the user.
     */
    public static void printErrorMessage(Task.Type type) {
        if (type == Task.Type.TODO) {
            System.out.println("Invalid format. Please enter the todo format as below:");
            System.out.println(Ui.TODO_FORMAT + "\n");
        } else if (type == Task.Type.DEADLINE) {
            System.out.println("Invalid format. Please enter the deadline format as below:");
            System.out.println(Ui.DEADLINE_FORMAT + "\n");
        } else {
            System.out.println("Invalid format. Please enter the event format as below:");
            System.out.println(Ui.EVENT_FORMAT + "\n");
        }
    }
}
