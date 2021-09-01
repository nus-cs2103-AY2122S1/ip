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
     * Constructs a Ui instance
     */
    public Ui(Petal petal) {
        this.petal = petal;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        petal.greetUser();
    }

    public void readCommand(String userInput) {
        petal.run(userInput);
    }

    public void sendToGui(String message, String reply) {
        mainWindow.sendUserReply(message, reply);
    }

    public void sendToGui(String reply) {
        mainWindow.sendUserReply(reply);
    }

}
