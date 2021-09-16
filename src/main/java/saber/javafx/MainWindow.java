package saber.javafx;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import saber.Saber;
import saber.ui.ByeUI;
import saber.ui.SaberUI;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Saber saber;
    private SaberUI saberGeneralUI;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image saberImage = new Image(this.getClass().getResourceAsStream("/images/saberprofile.png"));

    /**
     * Initializes the UI
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.saberGeneralUI = new SaberUI();
        dialogContainer.getChildren().addAll(
                DialogBox.getSaberDialog(saberGeneralUI.getGreeting(), saberImage)
        );
    }

    public void setSaber(Saber d) {
        saber = d;
        if (saber.getHasInitializationError()) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getSaberDialog(saberGeneralUI.getStorageLoadingError(), saberImage)
            );
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Saber's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        ByeUI byeUI = new ByeUI();
        String input = userInput.getText();
        String response = saber.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSaberDialog(response, saberImage)
        );
        userInput.clear();

        if (response.equals(byeUI.getSuccessMessage())) {
            // Set text field to exiting
            userInput.setText("Exiting...");
            // Disable text field
            userInput.setEditable(false);
            // Disable enter on text field
            userInput.onActionProperty().set(event->{});
            // Disable send button
            sendButton.setDisable(true);
            new Timer().schedule(new TimerTask() {
                public void run () {
                    Platform.exit();
                    System.exit(0);
                }
            }, 3000);
        }
    }
}
