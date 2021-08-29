package duke.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import duke.Duke;
import org.w3c.dom.Text;


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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserAvatar.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/LollipopAvatar.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    public void sendFirstMessage(String message) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(message, dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput(ActionEvent event) {
        String input = userInput.getText();
        if (input.equals("bye")) { // Not ideal, how to fix?
            handleExitProgram(event);
        }

        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    private void handleExitProgram(ActionEvent event) {
        Object o = event.getSource();
        if (o instanceof Button) {
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        } else {
            ((Stage)(((TextField)event.getSource()).getScene().getWindow())).close();
        }

    }
}