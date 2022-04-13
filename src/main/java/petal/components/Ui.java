package petal.components;

import petal.Petal;
import petal.gui.MainWindow;

/**
 * The Ui is responsible for handling the output, and interactions
 * with the user
 */
public class Ui {

    private final Petal petal;
    private MainWindow mainWindow;

    /**
     * Constructs an Ui instance
     */
    public Ui(Petal petal) {
        this.petal = petal;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        petal.greetUser();
    }

    /**
     * Reads the command and passes into Petal for comprehension
     *
     * @param userInput The message the user has given
     */
    public void readCommand(String userInput) {
        petal.run(userInput);
    }

    /**
     * Sends the reply and user input back to the main window to display
     *
     * @param message The user input
     * @param reply The reply to the message
     */
    public void sendToGui(String message, String reply) {
        assert mainWindow != null;
        mainWindow.sendUserReply(message, reply);
        if (reply.equals(Responses.GOODBYE.toString())) {
            mainWindow.terminatePetal();
        }
    }

    /**
     * Sends reply to the main window to display
     *
     * @param reply The reply to the message
     */
    public void sendToGui(String reply) {
        assert mainWindow != null;
        mainWindow.sendUserReply(reply);
    }

}
