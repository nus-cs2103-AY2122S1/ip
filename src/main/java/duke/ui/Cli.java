package duke.ui;

import java.util.Scanner;

import duke.Message;
import duke.TaskList;
import duke.storage.TaskStorage;

/**
 * Represents a command line interface (CLI) that the user interacts with.
 */
public class Cli extends Ui {
    private static final String HORIZONTAL_LINE =
            "____________________________________________________________";
    private Scanner scanner;

    /**
     * Constructs a CLI with the user's storage and task list,
     * initialising a scanner to deal with user input.
     *
     * @param taskStorage The user's storage of tasks in the hard disk.
     * @param taskList The user's list of tasks.
     */
    public Cli(TaskStorage taskStorage, TaskList taskList) {
        super(taskStorage, taskList);
        scanner = new Scanner(System.in);
    }

    /**
     * Scans user input, delegates the various cases of handling
     * user input to the respective functions and prints out the
     * response to the user input.
     */
    public void scanUserInput() {
        while (getIsRunning()) {
            String userInput = scanner.nextLine();
            String responseMessage = getResponse(userInput);
            printMessage(responseMessage);
        }
        scanner.close();
    }

    /**
     * Prints the message specified in a formatted manner.
     *
     * @param message A string to be printed.
     */
    public void printMessage(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        String welcomeMessage = Message.getWelcomeMessage();
        printMessage(welcomeMessage);
        scanUserInput();
    }

}
