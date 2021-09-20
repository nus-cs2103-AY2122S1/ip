package duke.ui;

import duke.Main;
import javafx.stage.Stage;

import java.util.Scanner;

/**
 * Represents an interface between the program and the user.
 */
public class UserInterface {
    private MainWindow mainWindow;

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
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        String greetingMessage = "Hello from\n" + logo;
        mainWindow.print(greetingMessage);
    }

    /**
     * Writes a farewell message.
     */
    public void displayFarewell() {
        mainWindow.print("Bye. Hope to see you again soon!");
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
