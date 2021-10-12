package duke;

import duke.commands.CommandResult;
import duke.util.Response;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/pepe.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/pepeduke.jpg"));

    /**
     * A method for initializing the MainWindow of the Duke GUI.
     */
    @FXML
    public void initialize() {
        Color c = Color.web("#95B8D1");
        BackgroundFill backgroundFill = new BackgroundFill(c, CornerRadii.EMPTY, Insets.EMPTY);
        dialogContainer.setBackground(new Background(backgroundFill));

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(new Response().showStartMsg(), dukeImage)
        );
    }

    /**
     * Attaches the Duke object that processes user input.
     *
     * @param duke The Duke instance to be attached to the GUI.
     */
    public void setDuke(Duke duke) {
        this.duke = duke;

        if (!this.duke.hasLoadedFile()) {
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog(new Response().showLoadError(), dukeImage)
            );
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and one containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        CommandResult response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response.getMessage(), dukeImage)
        );
        userInput.clear();

        if (response.getExitStatus()) {
            System.exit(0);
        }
    }
}
