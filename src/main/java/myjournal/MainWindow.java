package myjournal;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/flower.png"));
    private Image journalImage = new Image(this.getClass().getResourceAsStream("/images/journal.png"));

    /**
     * Initializes MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getMyJournalDialog(Ui.welcomeMessage(), journalImage));
        isOffline = false;
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
            initialize();
        }
        String input = userInput.getText();
        if (input.equals("bye")) {
            isOffline = true;
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getMyJournalDialog(Ui.goodByeMessage(), journalImage));
        } else {
            String response = myJournal.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getMyJournalDialog(response, journalImage)
            );
        }
        userInput.clear();
    }
}
