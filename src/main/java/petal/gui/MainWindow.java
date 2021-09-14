package petal.gui;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import petal.components.Ui;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    //FXML components
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    private Ui ui;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Ui instance
     * @param ui Ui instance to be set
     */
    public void setUi(Ui ui) {
        this.ui = ui;
        ui.setMainWindow(this);
    }

    /**
     * Sends the output to the interface to be read by the user
     *
     * @param input The user's input
     * @param output The output
     */
    public void sendUserReply(String input, String output) {
        dialogContainer.getChildren().addAll(
                UserDialogBox.getUserDialog(input),
                PetalDialogBox.getPetalDialog(output)
        );
    }

    /**
     * Sends the output to the interface to be read by the user
     * This method is used when it is one-sided (the user does not need
     * to send an input)
     *
     * @param output The output message
     */
    public void sendUserReply(String output) {
        dialogContainer.getChildren().addAll(
                PetalDialogBox.getPetalDialog(output)
        );
    }

    /**
     * Terminates the current bot instance by exiting the GUI
     */
    public void terminatePetal() {
        Platform.exit();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText().trim();
        ui.readCommand(input);
        userInput.clear();
    }
}
