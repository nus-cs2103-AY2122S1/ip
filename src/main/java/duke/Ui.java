package duke;

import java.util.Scanner;

/**
 * Handles user interaction.
 */
public class Ui {
    public static final String LINE = "____________________________________________________________";
    public static final String INDENT = "    ";

    /**
     * Empty Constructor to create Ui object.
     */
    public Ui() {
    }
    
    /**
     * Prints all Strings in msgs to screen with the appropriate format.
     *
     * @param msgs Variable length of String messages.
     */
    public void toScreen(String... msgs) {
        System.out.println(INDENT + LINE);
        for (String msg : msgs) {
            System.out.println(INDENT + msg);
        }
        System.out.println(INDENT + LINE);
    }

    public void firstWelcome() {
        toScreen("Hello, I'm Duke!", "How can I help you?");
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
