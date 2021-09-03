package kermit;

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

// todo close application properly
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

    private Kermit kermit;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/cookiemonster.png"));
    private Image kermitImage = new Image(this.getClass().getResourceAsStream("/images/kermit.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setKermit(Kermit k) {
        kermit = k;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = kermit.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getKermitDialog(response, kermitImage)
        );

        // @@author endriu_ - reused
        //Reused from https://stackoverflow.com/a/21996863/9078664
        if (response == "Bye. Hope to see you again soon!") {
            new Timer().schedule(new TimerTask() {
                public void run () {
                    Platform.exit();
                }
            }, 3000);
        }

        userInput.clear();


    }
}
