package duke;

import duke.ui.GraphicalUi;
import duke.ui.Ui;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Controller which manages the main window. Provides the layout for the other controls.
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
     * Binds the scroll pane position to the dialog container's height to always scroll to the
     * bottom of the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke duke) {
        assert duke != null;
        this.duke = duke;

        Ui ui = new GraphicalUi(dialogContainer, dukeImage);
        duke.setUi(ui);
        ui.printGreeting();
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        userInput.clear();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
        );

        // Close the app if possible.
        boolean shouldExit = duke.executeCommand(input);
        if (shouldExit) {
            Scene scene = this.sendButton.getScene();
            if (scene != null) {
                Window window = scene.getWindow();

                if (window instanceof Stage) {
                    Stage stage = (Stage) window;
                    stage.close();
                }
            }
        }
    }
}
