package duke.util;

import java.util.Scanner;

/**
 * Class wrapping all User Interface related functions and details
 */
public class Ui {
    public static final String INDENT = "    ";
    private static final String TOP_BORDER = "____________________________________";
    private static final String BOTTOM_BORDER = "------------------------------------";
    public static final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String WELCOME_STATEMENT = "Hello! How would you like to manage your tasks today?\n";
    public static final String CLOSING_STATEMENT = "Bye, hope to see you again! :) \n" +
            INDENT + "shutting down...";

    /**
     * Constructor for Ui class. Prints welcome message.
     */
//    public Ui() {
//        this.start();
//        printFormatted("Hello from\n" + LOGO);
//    }

    /**
     * Starts the Ui and begins accepting inputs.
     */
//    public void start() {
//        this.isRunning = true;
//    }

    /**
     * Helper function to print text in formatted manner
     *
     * @param text text to be printed
     */
//    public static void printFormatted(String text) {
//        assert !text.equals("") : "An empty text should not be print formatted";
//
//        String textWithBorders = TOP_BORDER + "\n"+  text + "\n" + BOTTOM_BORDER + "\n";
//        String[] lines = textWithBorders.split("\n");
//        for (String line : lines) {
//            System.out.println(Ui.INDENT + line);
//        }
//    }
}
