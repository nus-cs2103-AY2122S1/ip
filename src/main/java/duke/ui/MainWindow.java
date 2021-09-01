package duke.ui;

import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

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
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            exit();
        }
    }

    /**
     * Greets the user by displaying the greeting message when the gui starts running.
     */
    public void greetTheUser() {
        String greetingMessage = duke.getGreetingMessage();
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(greetingMessage, dukeImage)
        );
    }

    /**
     * Alerts the user and terminates the duke program.
     */
    public void exit() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Exit");
        alert.setHeaderText(null);
        alert.setContentText("Bye. Hope to see you again soon!");
        alert.getButtonTypes().setAll(new ButtonType("See ya!"));
        alert.showAndWait();
        Platform.exit();
    }
}
