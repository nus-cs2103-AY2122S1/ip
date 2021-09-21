package duke.ui;

import java.util.Arrays;
import java.util.Scanner;

/**
 * This is the Ui class for UI display.
 */
public class Ui {
    private static final String HORIZONTAL_LINE =
            "    ____________________________________________________________";
    private static final String INDENTATION = "     ";
    private final Scanner scanner;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays logo.
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
     * Displays greet.
     */
    public void greet() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Hello! I'm Duke");
        System.out.println(INDENTATION + "What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays command.
     */
    public void echo(String command) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + command);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays messages.
     */
    public void show(String... messages) {
        System.out.println(HORIZONTAL_LINE);
        Arrays.stream(messages)
                .forEach(message -> System.out.println(INDENTATION + message));
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays bye.
     */
    public void bye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Returns one line of command.
     *
     * @return One line of commands.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
