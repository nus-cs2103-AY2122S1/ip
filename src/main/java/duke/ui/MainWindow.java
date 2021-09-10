package duke.ui;

import duke.Duke;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/KaigainikiUser.jpeg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/SuiseiDuke.jpeg"));

    /**
     * Initializes the GUI upon startup of the application.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greetings = "Hey! I'm TsunDuke!\nWhat do you want?\n"
            + "...It's not like I want to help you or anything!";
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(greetings, dukeImage)
        );
    }

    /**
     * Sets an instance of Duke to be used for the application.
     *
     * @param d The Duke to be set.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        DialogBox dukeDialog = duke.checkIfErrorMessage(response)
            ? DialogBox.getDukeErrorDialog(response, dukeImage)
            : DialogBox.getDukeDialog(response, dukeImage);

        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            dukeDialog
        );

        if (response.equals("Hmph! It's not like I want to see you again or anything!")) {
            //@@author James_D
            //Reused from
            // https://stackoverflow.com/questions/27334455/how-to-close-a-stage-after-a-certain-amount-of-time-javafx
            // with minor modifications
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
            //@author
        } else {
            userInput.clear();
        }
    }
}
