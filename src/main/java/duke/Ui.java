package duke;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

/**
 * Encapsulates a class that deals with interactions from the user and displaying Duke's responses to the user.
 */
public class Ui {
    private MainWindow mainWindowController;

    /**
     * Constructor for a Ui.
     */
    public Ui() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        this.mainWindowController = fxmlLoader.<MainWindow>getController();
    }

    /**
     * Displays a given message.
     *
     * @param message
     */
    public void showMessage(String message) {
        mainWindowController.displayDukeDialog(message);
    }

    /**
     * Displays an error message for an error in loading a data file.
     */
    public void showLoadingError() {
        String message = "Oops! There was an error loading your data file. "
                + "A new data file has been created for you.";
        showMessage(message);
    }

    /**
     * Displays an error message for an exception thrown in saving to a data file.
     *
     * @param e The exception thrown.
     */
    public void showSavingError(IOException e) {
        String message = "Oops! There was an error saving to your data file." + e.getMessage();
        showMessage(message);
    }

    /**
     * Generates a welcome message.
     *
     * @return A welcome message from Duke.
     */
    public static String getWelcomeMessage() {
        return "Hello! I'm Duke, your personal assistant. \nWhat can I do for you?";
    }
}
