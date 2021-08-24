package duke;

import java.util.Scanner;

/**
 * Encapsulates a class that contains methods for interacting with the user
 */
public class UI {
    private final Scanner sc;

    public UI() {
        sc = new Scanner(System.in);
    }

    /**
     * Reads the next user input
     *
     * @return The user input as a String
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Returns a String that is the provided String wrapped with divider lines
     *
     * @param s The provided String
     * @return The new String that is formatted
     */
    public String formatMessage(String s) {
        return "    ____________________________________________________________\n     " +
                s + "\n" +
                "    ____________________________________________________________";
    }

    /**
     * Returns a String that is all the Strings in the provided array wrapped with divider lines
     *
     * @param messages The provided String Array
     * @return The new String that is formatted
     */
    public String formatMessage(String[] messages) {
        StringBuilder res = new StringBuilder("    ____________________________________________________________" );
        for(String s : messages) {
            res.append("\n    ").append(s);
        }
        res.append("\n    ____________________________________________________________");
        return res.toString();
    }

    /**
     * Prints the welcome message
     */
    public void welcomeMessage() {
        print("Hello! I'm duke.Duke\n" + "     What can I do for you?");
    }

    /**
     * Prints the good bye message
     */
    public void goodByeMessage() {
        print("Bye. Hope to see you again soon!");
    }

    /**
     * Prints an error message upon receiving an unrecognised command
     */
    public void unrecognisedCommand() {
        print("That is not a recognised command");
    }

    /**
     * Prints out the given string after formatting it with formatMessage
     *
     * @param s The provided String Array
     */
    public void print(String s) {
        System.out.println(formatMessage(s));
    }

    /**
     * Prints out the given string array after formatting it with formatMessage
     *
     * @param s The provided String Array
     */
    public void print(String[] s) {
        System.out.println(formatMessage(s));
    }
}
