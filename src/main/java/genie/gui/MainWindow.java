package genie.gui;

import genie.Genie;
import genie.common.Message;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Controller for genie.gui.MainWindow. Provides the layout for the other controls.
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

    private Genie genie;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Aladdin.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Genie.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getGenieDialog(Message.WELCOMEMESSAGE, dukeImage)
        );
    }

    public void setGenie(Genie d) {
        genie = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.length() == 0) {
            return;
        }
        String response = genie.getResponse(input);
        
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getGenieDialog(response, dukeImage)
        );

        if (input.contains("bye")) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
            TimerTask task = new TimerTask() {
                public void run() {
                    Platform.exit();
                }
            };
            Timer timer = new Timer("Timer");
            long delay = 2000;
            timer.schedule(task, delay);
        }
        userInput.clear();
    }
    
}