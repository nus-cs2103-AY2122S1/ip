package ui.view;

import chatbot.IChatbot;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;
import java.util.logging.Logger;

// @author Jeffry Lum
// Reused from https://se-education.org/guides/tutorials/javaFxPart4.html
// with minor modifications

/**
 * Main.Main Gui that creates the window for chatting.
 */
public class MainWindowController extends AnchorPane implements IWindowController {
    private static final Logger logger = Logger.getLogger(MainWindowController.class.getName());
    private final Image userImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/User.png")));
    private final Image chatbotImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/Dude.jpg")));
    private IChatbot chatbot = null;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    
    /**
     * Sets the chatbot for this Window.
     *
     * @param chatbot Chatbot.
     */
    public void setChatbot(IChatbot chatbot) {
        this.chatbot = chatbot;
    }
    
    /**
     * Initializes the component.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }
    
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        try {
            String input = userInput.getText();
            System.out.println();
            addUserDialog(input);
            if (chatbot != null) {
                chatbot.processResponse(input);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            userInput.clear();
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void addUserDialog(String s) {
        System.out.println(this.dialogContainer.toString());
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(s, userImage));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void addBotDialog(String s) {
        System.out.println(this.dialogContainer.toString());
        dialogContainer.getChildren().addAll(DialogBox.getBotDialog(s, chatbotImage));
    }
}
