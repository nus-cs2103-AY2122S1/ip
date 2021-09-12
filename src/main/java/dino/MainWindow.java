package dino;

import dino.util.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

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

    private Dino dino;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Nature.jpg")));
    private final Image dinoImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DinoBot.jpg")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDinoDialog(Ui.greeting(), dinoImage)
        );
    }

    public void setDino(Dino d) {
        dino = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input
     * and the other containing Dino's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = dino.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDinoDialog(response, dinoImage)
        );
        userInput.clear();
    }
}