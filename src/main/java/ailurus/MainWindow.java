package ailurus;

import javafx.application.Platform;
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

    /**
     * Initialise FXML window
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        //dialogContainer.getChildren().add(DialogBox.getAilurusDialog(ui.showWelcome(), ailurusImage));
    }

    /**
     * Set and initalise Ailurus bot
     * @param d Ailurus object to be initialised with
     */
    public void setAilurus(Ailurus d) {
        ailurus = d;
        ui = new Ui();
        dialogContainer.getChildren().add(DialogBox.getAilurusDialog(ui.showWelcome(), ailurusImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Ailurus' reply and then appends them to
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
        if (Ailurus.isExit()) {
            Platform.exit();
        }
        userInput.clear();
    }
}
