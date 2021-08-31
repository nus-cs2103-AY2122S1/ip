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
 * Main Gui that creates the window for chatting.
 */
public class MainWindow extends AnchorPane {
    private static final Logger logger = Logger.getLogger(DialogBox.class.getName());
    private final Image userImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/User.png")));
    private final Image chatbotImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/Dude.jpg")));
    private IChatbot IChatbot = null;
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
     * @param IChatbot Chatbot.
     */
    public void setChatbot(IChatbot IChatbot) {
        this.IChatbot = IChatbot;
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
            if (IChatbot != null) {
                IChatbot.processResponse(input);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            userInput.clear();
        }
    }
    
    private void addUserDialog(String s) {
        System.out.println(this.dialogContainer.toString());
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(s, userImage));
    }
    
    /**
     * Displays the dialog box for the bot which can be used when the bot wants to announce without user inputs.
     *
     * @param s String to be displayed.
     */
    public void addBotDialog(String s) {
        System.out.println(this.dialogContainer.toString());
        dialogContainer.getChildren().addAll(DialogBox.getBotDialog(s, chatbotImage));
    }
}
