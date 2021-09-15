package agent.gui;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import agent.Agent;
import agent.exceptions.DukeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
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

    private Agent agent;
    private final Image userImage = new Image("/images/user.jpg");
    private final Image agentImage = new Image("/images/agent.jpg");

    @FXML
    private void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Connects the chat bot to the UI and triggers set up actions including
     * greeting the user.
     *
     * @param agent instance of <code>Duke</code> to handle chat bot logic.
     */
    public void linkChatBotAgent(Agent agent) {
        if (agent == null) {
            return;
        }
        assert agentImage != null : "Agent image not found.";
        assert dialogContainer != null : "No dialog container found.";
        this.agent = agent;
        dialogContainer.getChildren().add(
                DialogBox.generateAgentDialogBox(agent.greetUser(), agentImage)
        );
    }

    /**
     * Displays provided text as Chat bot message.
     *
     * @param agentMessage message to be displayed.
     */
    public void displayAgentMessage(String agentMessage) {
        assert agentImage != null : "Agent image not found.";
        assert dialogContainer != null : "No dialog container found.";
        dialogContainer.getChildren().add(
                DialogBox.generateAgentDialogBox(agentMessage, agentImage)
        );
    }

    /**
     * Disables user input text field and send button.
     */
    private void disableUserInput() {
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
            agentResponse = agent.respondToUserInput(userInput);
        } catch (DukeException de) {
            agentResponse = de.getMessage();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.generateUserDialogBox(userInput, userImage),
                DialogBox.generateAgentDialogBox(agentResponse, agentImage)
        );
        userChatInputField.clear();

        if (agent.isExited()) {
            shutDown(500);
        }
    }

    /**
     * Disables user input and shuts down the agent.
     *
     * @param delayMilliseconds delay before terminating the program in milliseconds.
     */
    public void shutDown(int delayMilliseconds) {
        // Solution adapted from
        // https://stackoverflow.com/questions/15747277/how-to-make-java-program-exit-after-a-couple-of-seconds
        disableUserInput();
        Executors.newSingleThreadScheduledExecutor()
                .schedule(() -> System.exit(0), delayMilliseconds, TimeUnit.MILLISECONDS);
    }
}
