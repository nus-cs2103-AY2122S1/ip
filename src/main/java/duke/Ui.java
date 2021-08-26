package duke;

import java.util.Scanner;

public class Ui {

    private static final String HORIZONTAL_LINE = "_____________________________________________________________________";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String GREETING = "Hello! I am \n"
            + LOGO
            + "The awesome bot helper! \n"
            + "How can I help you today?";

    private static final String GOODBYE = "Bye. Hope to see you again soon!";
    private final Scanner myScanner;

    public Ui() {
        myScanner = new Scanner(System.in);  // Create a Scanner object
    }

    private static void reply(String output) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(output);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints greetings & goodbyes
     **/
    public void greet(Boolean isGreeting) {
        String printMessage = isGreeting
                ? GREETING
                : GOODBYE;
        reply(printMessage);
    }

    /**
     * Returns the next line of user input
     **/
    public String getInput() {
        String userInput = myScanner.nextLine();
        return userInput;
    }

    /**
     * Prints a string
     **/
    public void print(String s) {
        reply(s);
    }

    /**
     * Prints error message of an exception
     **/
    public void printException(Exception e) {
        reply(e.getMessage());
    }

}
