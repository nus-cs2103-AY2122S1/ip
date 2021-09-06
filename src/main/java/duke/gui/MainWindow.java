package duke.gui;

import duke.Duke;
import duke.exceptions.DukeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;

/**
 * Controller UI class which separates the UI handling concerns from Chat bot logic.
 *
 * @author kevin9foong
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userChatInputField;
    @FXML
    private Button sendButton;

    private Duke duke;
    private Image userImage = new Image("/images/user.png");
    private Image dukeImage = new Image("/images/duke.png");

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setUserImage(Image userImage) {
        this.userImage = userImage;
    }

    public void setDukeImage(Image dukeImage) {
        this.dukeImage = dukeImage;
    }

    public void setBackgroundImage(Image backgroundImage) {
        assert dialogContainer != null;
        dialogContainer.setBackground(new Background(new BackgroundImage(
                backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT
        )));
    }

    /**
     * Connects the Duke chat bot to the UI and triggers set up actions including
     * greeting the user.
     *
     * @param duke instance of <code>Duke</code> to handle chat bot logic.
     */
    public void linkDuke(Duke duke) {
        if (duke == null) {
            return;
        }
        assert dukeImage != null : "Agent image not found.";
        assert dialogContainer != null : "No dialog container found.";
        this.duke = duke;
        dialogContainer.getChildren().add(
                DialogBox.generateAgentDialogBox(duke.greetUser(), dukeImage)
        );
    }

    /**
     * Displays provided text as Chat bot message.
     *
     * @param agentMessage message to be displayed.
     */
    public void displayAgentMessage(String agentMessage) {
        assert dukeImage != null : "Agent image not found.";
        assert dialogContainer != null : "No dialog container found.";
        dialogContainer.getChildren().add(
                DialogBox.generateAgentDialogBox(agentMessage, dukeImage)
        );
    }

    /**
     * Disables user input after prompting user to exit.
     */
    public void disableUserInput() {
        sendButton.setDisable(true);
        userChatInputField.setDisable(true);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userInput = userChatInputField.getText();
        String agentResponse;
        try {
            agentResponse = duke.respondToUserInput(userInput);
        } catch (DukeException de) {
            agentResponse = de.getMessage();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.generateUserDialogBox(userInput, userImage),
                DialogBox.generateAgentDialogBox(agentResponse, dukeImage)
        );
        userChatInputField.clear();
    }
}
