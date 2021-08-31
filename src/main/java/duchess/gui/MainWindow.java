package duchess.gui;

import duchess.main.Duchess;
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
    /** A scroll pane to vertical scroll.*/
    @FXML
    private ScrollPane scrollPane;
    /** A VBox to hold text and images.*/
    @FXML
    private VBox dialogContainer = new VBox(100);
    /** The user's input text field.*/
    @FXML
    private TextField userInput;
    /** The user's submit button.*/
    @FXML
    private Button sendButton;
    /** The Duchess that serves the user.*/
    private Duchess duchess;
    /** The display image of the Duchess.*/
    private Image duchessImage = new Image("file:src/main/resources/images/duchess.jpg");
    /** The display image of the user.*/
    private Image userImage = new Image("file:src/main/resources/images/user.png");

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuchess(Duchess d) {
        duchess = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duchess.getResponse(input);
        if (response.equals("I bid thee farewell")) {
            Stage stage = (Stage)sendButton.getScene().getWindow();
            stage.close();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDuchessDialog(response, duchessImage)
        );
        userInput.clear();
    }
}