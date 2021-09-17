package duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the MainWindow front-end.
     *
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userInput.setPromptText("Enter your command here...");
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.isBlank()) {
            return;
        }
        assert !input.isBlank() : "Input has to be not empty!";
        String trimmedInput = input.trim();
        if (trimmedInput.equals("bye")) {
            Platform.exit();
        }
        String[] responses;
        try {
            responses = duke.getResponse(trimmedInput);
        } catch (Exception e) {
            responses = null;
        }
        if (responses == null) {
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog("OOPS!!! I don't understand your command: " + trimmedInput, dukeImage)
            );
            userInput.clear();
            return;
        }
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(String.join("\n", responses), dukeImage)
        );

        userInput.clear();
    }


    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
}
