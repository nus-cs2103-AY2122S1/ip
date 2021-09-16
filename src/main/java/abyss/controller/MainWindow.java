package abyss.controller;

import abyss.Abyss;
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
    private static final String START_MESSAGE = "Hello beautiful. Welcome to the Abyss. What can we do for you today?";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Abyss abyss;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Six.png"));
    private Image abyssImage = new Image(this.getClass().getResourceAsStream("/images/Gnome.png"));

    /**
     * Initializes Abyss window with welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
            AbyssDialog.getDialog(START_MESSAGE, abyssImage)
        );
    }

    public void setAbyss(Abyss d) {
        abyss = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = abyss.getResponse(input);
        dialogContainer.getChildren().addAll(
            UserDialog.getDialog(input, userImage),
            AbyssDialog.getDialog(response, abyssImage)
        );
        userInput.clear();
    }
}
