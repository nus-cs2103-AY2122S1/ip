package duke.util;

import java.util.Scanner;

/**
 * Ui class to encapsulate display messages and handle input/output of duke.
 */
public class Ui {
    private final Scanner sc;
    /**
     * Constructor method of Ui.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Reads the user input.
     *
     * @return User input string.
     */
    public String readNextLine() {
        return sc.nextLine().trim();
    }

    /**
     * Terminates the program.
     *
     * @return Goodbye message.
     */
    public String exit() {
        sc.close();
        return bye();
    }

    /**
     * Returns the welcome message when the bot is first called.
     *
     * @return Welcome message.
     */
    public static String greet() {
        return "Hiiii~ I'm Laa-Laa! Just so you know I'm not a real human...\n" 
                + "I'm here to assist you in simple task management.\n" 
                + "How can I help you? :)";
    }

    /**
     * Returns the goodbye message when the program is about to terminate.
     *
     * @return Goodbye message.
     */
    public static String bye() {
        return "Bye. Hope to see you again soon!";
    }


}
