import duke.Duke;
import duke.Response;
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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/dog.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/man.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Response response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response.toString(), dukeImage)
        );

        if (!response.isContinue()) {
            duke.finish();
            closeStage();
        }

        userInput.clear();
    }

    /**
     * Close the window
     */
    @FXML
    private void closeStage() {
        // Solution below adapted from https://github.com/chiamyunqing/ip/blob/master/src/main/java/MainWindow.java
        Stage stage = (Stage) userInput.getScene().getWindow();
        stage.close();
    }
}
