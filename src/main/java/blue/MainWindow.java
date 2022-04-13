package blue;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


/**
 * Controller for MainWindow. Provides the layout for the other controls.
 * This code is reused from SE-EDU JavaFX tutorial,
 * available at: https://se-education.org/guides/tutorials/javaFx.html
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

    private Blue blue;

    private final Image userImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image blueImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaBlue.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setBlue(Blue b) {
        blue = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Blue's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = blue.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBlueDialog(response, blueImage)
        );
        assert dialogContainer.getChildren().size() > 0 : "Dialog container should already contain dialogs";
        blue.save();
        userInput.clear();
    }
}
