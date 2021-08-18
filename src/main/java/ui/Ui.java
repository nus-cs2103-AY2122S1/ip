package ui;

import java.util.Scanner;

/**
 * The is the Ui class for UI display.
 *
 * @author  HU JIAJUN
 * @version %I%, %G%
 * @since   1.0
 */

public class Ui {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________";
    private static final String INDENTATION = "     ";
    private final Scanner scanner;

    /**
     * This is constructor method of Ui.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Display logo.
     */
    public void logo() {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Display greet.
     */
    public void greet() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Hello! I'm Duke");
        System.out.println(INDENTATION + "What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Display command.
     */
    public void echo(String command) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + command);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Display messages.
     */
    public void show(String... messages) {
        System.out.println(HORIZONTAL_LINE);
        for (String message: messages) {
            System.out.println(INDENTATION + message);
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Display bye.
     */
    public void bye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Read one line of command.
     *
     * @return one line of command
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
