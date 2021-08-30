package duke.ui;

import java.util.Arrays;
import java.util.Scanner;

/**
 * A Ui class to manage the user input and output display
 *
 * @author KelvinSoo
 * @version A-MoreOOP
 */
public class Ui {

    // Name & logo of the chatbot
    private final String CHATBOT_NAME = "Barry";
    private final String LOGO = " ____          _____  _______     __\n"
            + "|  _ \\   /\\   |  __ \\|  __ \\ \\   / /\n"
            + "| |_) | /  \\  | |__) | |__) \\ \\_/ /\n"
            + "|  _ < / /\\ \\ |  _  /|  _  / \\   /\n"
            + "| |_) / ____ \\| | \\ \\| | \\ \\  | |\n"
            + "|____/_/    \\_\\_|  \\_\\_|  \\_\\ |_|";

    private final Scanner sc;

    /**
     * A constructor to initialize a Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints the greeting text to user.
     */
    public void greetUser() {
        System.out.println(LOGO);
        printStringInBox(String.format("Hello! I'm %s \nWhat can I do for you?", CHATBOT_NAME));
    }

    /**
     * Print exit Message.
     */
    public void exit() {
        printStringInBox("Bye. Hope to see you again soon!");
    }

    /**
     * Print a given string in a box.
     *
     * @param string The string to be formatted
     */
    public void printStringInBox(String string) {
        String[] textLine = string.split("\n");
        int maxLength = Arrays.stream(textLine).map(String::length).max(Integer::compareTo).orElse(-1);
        String lineStart = "." + "-".repeat(maxLength + 2) + ".";
        String lineEnd = "`" + "-".repeat(maxLength + 2) + "`";
        System.out.println(lineStart);
        for (String s : textLine) {
            System.out.printf("| %s%s |\n", s, " ".repeat(maxLength - s.length()));
        }
        System.out.println(lineEnd);
    }

    /**
     * Print a given error in a box.
     *
     * @param string The string to be formatted
     */
    public void printStringInErrorBox(String string) {
        String[] textLine = string.split("\n");
        int maxLength = Arrays.stream(textLine).map(String::length).max(Integer::compareTo).orElse(-1);
        String lineStart = "." + "+".repeat(maxLength + 2) + ".";
        String lineEnd = "`" + "+".repeat(maxLength + 2) + "`";
        System.out.println(lineStart);
        for (String s : textLine) {
            System.out.printf("+ %s%s +\n", s, " ".repeat(maxLength - s.length()));
        }
        System.out.println(lineEnd);
    }

    /**
     * Get the user input.
     *
     * @return The user input as a string
     */
    public String getCommand() {
        return sc.nextLine();
    }
}
