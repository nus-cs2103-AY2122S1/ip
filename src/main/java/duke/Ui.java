package duke;

import java.util.Scanner;

/**
 * Handles user interaction.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private static final String INDENT = "    ";

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
