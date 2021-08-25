package duke;

/**
 * Class which stores basic output of the bot
 */
public class Ui {

    private final String DIVIDER = ("\n_________________________\n");
    private final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";


    /**
     * prints the divider
     */
    protected void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * prints the welcome message
     */
    protected void showWelcome() {
        System.out.println("Hello from\n" + LOGO + "\nWhat can I do for you?");
    }

    /**
     * prints loading error message
     */
    public void showLoadingError() {
        System.out.println("OOPS!!! The file was no loaded properly");
    }
}
