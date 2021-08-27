package ui;

import java.util.Scanner;

import ui.message.ExitMessage;
import ui.message.GreetMessage;
import ui.message.Message;

/**
 * Encapsulates the object that interacts with the user through inputs and outputs.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Shows a message to greet the user when the program starts.
     */
    public void showWelcome() {
        GreetMessage message = new GreetMessage("Hello! I'm Duke, what shall we do today?");
        message.print();
    }

    /**
     * Shows a message to say goodbye to the user when the program exits.
     */
    public void showGoodbye() {
        ExitMessage message = new ExitMessage("Bye, see you again");
        message.print();
    }

    /**
     * Shows a message to the user.
     *
     * @param message Message to be shown.
     */
    public void showMessage(Message message) {
        message.print();
    }

    /**
     * Reads input messages by line.
     *
     * @return String of the input message.
     */
    public String readInputMessage() {
        return this.scanner.nextLine();
    }
}
