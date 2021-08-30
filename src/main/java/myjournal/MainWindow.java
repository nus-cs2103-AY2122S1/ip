package myjournal;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private MyJournal myJournal;

    private boolean isOffline = true;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setMyJournal(MyJournal d) {
        myJournal = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        if (isOffline) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getMyJournalDialog(Ui.welcomeMessage(), dukeImage));
            isOffline = false;
        } else {
            String input = userInput.getText();
            if (input.equals("bye")) {
                isOffline = true;
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(input, userImage),
                        DialogBox.getMyJournalDialog(Ui.goodByeMessage(), dukeImage));
            } else {
                String response = myJournal.getResponse(input);
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(input, userImage),
                        DialogBox.getMyJournalDialog(response, dukeImage)
                );
            }
        }
        userInput.clear();
    }
}
