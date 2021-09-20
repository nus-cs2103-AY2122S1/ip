package duke.ui;

/**
 * Represents an interface between the program and the user.
 */
public class UserInterface {
    private MainWindow mainWindow;
    public static final String LOGO
            = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String GREETING_MESSAGE = "Hello from\n" + LOGO;
    public static final String FAREWELL_MESSAGE = "Bye. Hope to see you again soon!";

    /**
     * Class constructor.
     */
    public UserInterface(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * Writes a greeting message.
     */
    public void displayGreeting() {
        mainWindow.print(GREETING_MESSAGE);
    }

    /**
     * Writes a farewell message.
     */
    public void displayFarewell() {
        mainWindow.print(FAREWELL_MESSAGE);
    }

    /**
     * Writes a message from a given String.
     * @param message The message to be written
     */
    public void print(String message) {
        mainWindow.print(message);
    }

    /**
     * Writes an error message
     * @param error The error message to be written
     */
    public void displayError(String error) {
        mainWindow.print(error);
    }
}
