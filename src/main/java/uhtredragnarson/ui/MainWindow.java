package uhtredragnarson.ui;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import uhtredragnarson.UhtredRagnarson;

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

    private UhtredRagnarson uhtredRagnarson;

    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/DaUser.jpg")));
    private final Image uhtredRagnarsonImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/DaDuke.jpeg")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setUhtredRagnarson(UhtredRagnarson d) {
        uhtredRagnarson = d;
        dialogContainer.getChildren().addAll(DialogBox.getUhtredRagnarsonDialog(
                uhtredRagnarson.showWelcomeMessage(), uhtredRagnarsonImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = uhtredRagnarson.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getUhtredRagnarsonDialog(response, uhtredRagnarsonImage)
        );
        userInput.clear();
    }
}
