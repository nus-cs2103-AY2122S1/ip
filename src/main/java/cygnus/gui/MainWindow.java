package cygnus.gui;

import cygnus.Cygnus;
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

    private Cygnus cygnus;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserImage.png"));
    private final Image cygnusImage = new Image(this.getClass().getResourceAsStream("/images/CygnusImage.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setCygnus(Cygnus d) {
        cygnus = d;
    }

    /**
     * Creates a dialog box containing the greeting message and appends it to the dialog container.
     */
    public void displayGreeting() {
        dialogContainer.getChildren()
                .add(DialogBox.getCygnusDialog("Hello! I'm Cygnus.\nWhat can I do for you?", cygnusImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Cygnus's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        assert userInput != null : "Text field is not initialized";
        String input = userInput.getText();
        String response = cygnus.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCygnusDialog(response, cygnusImage)
        );
        userInput.clear();
    }

}
