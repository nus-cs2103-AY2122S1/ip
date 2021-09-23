package duke.gui;

import duke.core.Duke;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates a dialog box containing Duke's reply and then appends it to the dialog container. Clears the user input
     * after processing.
     */
    @FXML
    private void handleUserInput() {
        String userInputText = userInput.getText();
        Label userInputLabel = new Label(userInputText);

        String[] dukeResponseAndExitStatus = duke.getResponseAndExitStatus(userInputText);
        String dukeResponseInString = dukeResponseAndExitStatus[0];
        boolean dukeShouldExit = dukeResponseAndExitStatus[1] == "1" ? true : false;
        Label dukeResponse = new Label(dukeResponseInString);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInputText, userImage),
                DialogBox.getDukeDialog(dukeResponseInString, dukeImage)
        );
        dialogContainer.setSpacing(10.0);
        userInput.clear();

        if (dukeShouldExit) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(e -> Platform.exit());
            delay.play();
        }
    }
}
