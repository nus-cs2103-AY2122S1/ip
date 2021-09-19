package myjournal;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Creates Main Window.
 *
 * @author Felissa Faustine.
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

    private MyJournal myJournal;

    private boolean isOffline = true;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/pig.png"));
    private Image journalImage = new Image(this.getClass().getResourceAsStream("/images/rabbit.png"));

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

    public void setMyJournal(MyJournal myJournal) {
        this.myJournal = myJournal;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing MyJournal's reply and then appends
     * them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        if (isOffline) {
            initialize();
        }
        String input = userInput.getText();
        if (input.equals("bye")) {
            isOffline = true;
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getMyJournalDialog(Ui.goodByeMessage(), journalImage));
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        } else {
            String response = myJournal.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getMyJournalDialog(response, journalImage)
            );
            userInput.clear();
        }
    }
}
