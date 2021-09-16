package duke;

import duke.exception.DukeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/PusheenUser200.png"));
    private Image happyBotImage = new Image(this.getClass().getResourceAsStream("/images/PusheenBot200.png"));
    private Image angryBotImage = new Image(this.getClass().getResourceAsStream("/images/PusheenAngry200.png"));


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    @FXML
    public void setDuke(Duke d) {
        duke = d;
        String startMessage;
        try {
            startMessage = duke.handleStart();
        } catch (DukeException e) {
            startMessage = e.getMessage();
        }
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(startMessage, happyBotImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        Image botImage;
        String input = userInput.getText();
        Response response = duke.handleCommands(input);
        String responseOutput = response.getMessage();

        if (response.hasError()) {
            botImage = angryBotImage;
        } else {
            botImage = happyBotImage;
        }

        //TODO: isTerminate
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getDukeDialog(responseOutput, botImage)
        );
        userInput.clear();
    }
}