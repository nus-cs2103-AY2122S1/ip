package duke.util;

import java.util.Scanner;

/**
 * Class wrapping all User Interface related functions and details
 */
public class Ui {
    private boolean isRunning;
    private Scanner sc;
    private static final String INDENT = "    ";
    private static final String TOP_BORDER = "____________________________________";
    private static final String BOTTOM_BORDER = "------------------------------------";
    private static final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String CLOSING_STATEMENT = "Bye, hope to see you again! :)";

    /**
     * Constructor for Ui class. Prints welcome message.
     */
    public Ui() {
        this.start();
        printFormatted("Hello from\n" + LOGO);
    }

    /**
     * Starts the Ui and begins accepting inputs.
     */
    public void start() {
        this.sc = new Scanner(System.in);
        this.isRunning = true;
    }

    /**
     * Closes the Ui and stops accepting inputs.
     */
    public void close() {
        sc.close();
        this.isRunning = false;
        printFormatted(CLOSING_STATEMENT);
    }

    /**
     * Returns user inputted command.
     *
     * @return user inputted command
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Checks if user has inputted a command
     *
     * @return boolean representing whether user has inputted a command
     */
    public boolean isPendingReply() {
        return sc.hasNext();
    }

    //getter
    public boolean isRunning() {
        return this.isRunning;
    }

    /**
     * Helper function to print text in formatted manner
     *
     * @param text text to be printed
     */
    public static void printFormatted(String text) {
        String textWithBorders = TOP_BORDER + "\n"+  text + "\n" + BOTTOM_BORDER + "\n";
        String[] lines = textWithBorders.split("\n");
        for (String line : lines) {
            System.out.println(Ui.INDENT + line);
        }
    }
}
