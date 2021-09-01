package eightbit.gui;

import eightbit.EightBit;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    private EightBit eightBit;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image eightBitImage = new Image(this.getClass().getResourceAsStream("/images/eightbit.png"));

    public void setEightBit(EightBit eightBit) {
        this.eightBit = eightBit;
    }

    /**
     * Starts the program and shows a welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getEightBitDialog("Hello! I'm 8-Bit!\nWhat can I do for you?", eightBitImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = eightBit.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getEightBitDialog(response, eightBitImage)
        );
        userInput.clear();

        if (response.equals("Bye. Hope to see you again soon!")) {
            Stage stage = (Stage) userInput.getScene().getWindow();
            stage.close();
        }
    }
}
