package ailurus;

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

    private Ailurus ailurus;
    private Ui ui;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Pixel.png"));
    private Image ailurusImage = new Image(this.getClass().getResourceAsStream("/images/Ailurus.jpg"));

    @FXML
    public void initialize() {
//        dialogContainer.getChildren().add(DialogBox.getAilurusDialog(ui.showWelcome(), ailurusImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setAilurus(Ailurus d) {
        ailurus = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ailurus.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAilurusDialog(response, ailurusImage)
        );
        if (Ailurus.isExit) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
        }
        userInput.clear();
    }
}
