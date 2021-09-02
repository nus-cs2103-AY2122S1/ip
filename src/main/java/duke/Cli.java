package duke;

import java.util.Scanner;

/**
 * Represents a command line interface (CLI) that the user interacts with.
 */
public class Cli extends Ui {
    private static final String HORIZONTAL_LINE =
            "____________________________________________________________";
    private Scanner scanner;

    /**
     * Constructs a CLI with the user's storage and tasks,
     * initialising a scanner to deal with user input.
     *
     * @param taskStorage The user's storage of tasks in the hard disk.
     * @param tasks The user's list of tasks.
     */
    public Cli(TaskStorage taskStorage, TaskList tasks) {
        super(taskStorage, tasks);
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
            handleUserInput(userInput);
            printMessage();
        }
        scanner.close();
    }

    /**
     * Prints the latest message in a formatted manner.
     */
    public void printMessage() {
        printMessage(getCurrMessage());
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
        printMessage(Message.getWelcomeMessage());
        scanUserInput();
    }

}
