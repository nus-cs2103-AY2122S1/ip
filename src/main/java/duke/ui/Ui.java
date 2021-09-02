package duke.ui;

import java.util.Scanner;

/**
 * The class that handles ui functions such as
 * printing messages and errors to user and reading from user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructor of Ui class.
     * Instantiates an Ui object that by default reads input from System.in
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }


    /**
     * Shows welcome message of Duke.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
    }


    /**
     * Reads user inputs.
     *
     * @return String of the line read from standard in.
     */
    public String readLine() {
        String input = scanner.nextLine();
        return input;
    }


    /**
     * Greets user.
     *
     * @param name User name to display.
     */
    public void greetUser(String name) {
        printHorizLine();
        System.out.println("Hello " + name + "!");
        System.out.println("I'm Duke");
        printHorizLine();
    }


    /**
     * Say goodbye to user.
     *
     * @param name User name to display.
     */
    public void sayBye(String name) {
        System.out.println("Bye " + name + ", hope to see you soon!");
    }


    /**
     * Prints a horizontal line.
     */
    public void printHorizLine() {
        System.out.println("————————————————————————————————————————");
    }


    /**
     * Prints the given message.
     *
     * @param msg The message to display.
     */
    public void printMsg(String msg) {
        System.out.println(msg);
    }


    /**
     * Prints the given error message.
     *
     * @param errorMsg The error message to display.
     */
    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

}
