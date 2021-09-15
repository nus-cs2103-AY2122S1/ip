package duke.ui;

import duke.Main;
import javafx.stage.Stage;

import java.util.Scanner;

/**
 * Represents an interface between the program and the user.
 */
public class UserInterface {
    Scanner stdin;
    private MainWindow mainWindow;

    /**
     * Class constructor.
     */
    public UserInterface(MainWindow mainWindow) {
        stdin = new Scanner(System.in);
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
        System.out.println("----------------------");
        System.out.println("Hello from\n" + logo);
        System.out.println("----------------------");
        System.out.println("What can I do for you?");
    }

    /**
     * Writes a farewell message.
     */
    public void displayFarewell() {
        System.out.println("----------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("----------------------");
    }

    /**
     * Writes a message from a given String.
     * @param message The message to be written
     */
    public void print(String message) {
        System.out.println(message);
    }

    /**
     * Writes an error message
     * @param error The error message to be written
     */
    public void displayError(String error) {
        System.err.println(error);
    }

    /**
     * Gets a nonempty line of user input
     * @return A string containing the user input
     */
    public String getResponse() {
        String response = "";
        while (response.equals("")) {
            response = stdin.nextLine();
        }
        return response;
    }
}
