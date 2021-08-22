package duke.ui;

import duke.data.exception.DukeException;

import java.util.Scanner;

/**
 * Deals with interactions with the user
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructor for Ui
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints greeting message
     */
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(wrapText("Hello from\n" + logo + "\nWhat can I do for you?"));
    }

    /**
     * Prints goodbye message
     */
    public void goodbye() {
        this.sc.close();
        System.out.println(wrapText("Bye. Hope to see you again soon!"));
    }

    /**
     * Prints error message
     * @param e the exception to print
     */
    public void showErrorMessage(DukeException e) {
        System.out.println(wrapText(e.toString()));
    }

    /**
     * Reads a line of user input
     * @return string of user input
     */
    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Prints given message
     * @param msg the message to print
     */
    public void print(String msg) {
        System.out.println(wrapText(msg));
    }

    /**
     * Wraps given text with line break
     * @param input the text to wrap
     * @return input wrapped with line breaks
     */
    private String wrapText(String input) {
        String lineBreak = "\n____________________________________________________________\n";
        return lineBreak + input + lineBreak;
    }
}
