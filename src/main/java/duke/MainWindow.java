package duke;

import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
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

    private Duke duke;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/penguin_1.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/penguin_2.png"));

    @FXML
    private void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        Duke dukeTemp = new Duke();
        dialogContainer.getChildren().addAll(
                DukeDialogBox.getDukeDialog(dukeTemp.getWelcomeMessage(), dukeImage),
                DukeDialogBox.getDukeDialog(dukeTemp.initialGetTasks(), dukeImage)
        );
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
                UserDialogBox.getUserDialog(input, userImage),
                DukeDialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (duke.isExit()) {
            new Timer().schedule(new TimerTask() {
                public void run () {
                    System.exit(0);
                }
            }, 2000);
        }
    }
}
