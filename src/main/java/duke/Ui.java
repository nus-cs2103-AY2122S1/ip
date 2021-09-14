package duke;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Represents the Ui object that deals with interactions with the user.
 */
public class Ui {
    private final Scanner sc;

    Ui() {
        sc = new Scanner(System.in);
    }

    public String getUserInput() {
        return sc.nextLine();
    }

    /**
     * Prints greeting message
     */
    public void greet() {
        reply("Hello! I'am Duke\n"
                + "What can I do for you?");
    }

    /**
     * Output box formatted message to screen.
     *
     * @param msg The message to be formatted.
     */
    public void reply(String msg) {
        String indentedMsg = Arrays.stream(msg.split("\n"))
                .map(s -> String.format("\t%s\n", s))
                .collect(Collectors.joining(""));
        System.out.printf("\t____________________________________\n"
                + "%s"
                + "\t____________________________________\n", indentedMsg);
    }

    /**
     * Handles any exceptions. Outputs the message to screen for now.
     *
     * @param e The exception to be handled.
     */
    public void handleError(Exception e) {
        reply(e.getMessage());
    }

}
