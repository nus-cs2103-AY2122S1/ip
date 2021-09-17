package lebron.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import lebron.Lebron;

//Solution below adapted from https://se-education.org/guides/tutorials/javaFx.html
//Credit to Jeffry Lum

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private final Image lebronImage = new Image(this.getClass().getResourceAsStream("/images/lebron.jpg"));
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/blank.png"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Lebron lebron;

    /**
     * Initialises the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("    Hello! I'm LebronChatBot\n"
                        + "    What can I do for you?\n", lebronImage)
        );
    }

    public void setLebron(Lebron l) {
        lebron = l;
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String lebronText = getResponse(userInput.getText());
        if (userText.equals("bye")) {
            Platform.exit();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getDukeDialog(lebronText, lebronImage)
        );
        userInput.clear();
    }

    @FXML
    private String getResponse(String input) {
        return lebron.run(input);
    }
}
