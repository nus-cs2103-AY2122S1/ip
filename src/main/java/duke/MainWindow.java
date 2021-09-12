package duke;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import javafx.scene.paint.Color;
import javafx.util.Duration;

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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/chicken.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duck.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.setBackground(new Background(
                new BackgroundFill(Color.FLORALWHITE, CornerRadii.EMPTY, Insets.EMPTY))
        );
    }

    public void setDuke(Duke d) {
        duke = d;
        Ui ui = new Ui();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDefaultDialog(ui.showWelcome(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(userInput.getText());
        boolean isExceptionThrown = response.split("\\s+", 2)[0].equals("OOPS!!!");
        if (isExceptionThrown) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeExceptionDialog(response, dukeImage)
            );
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDefaultDialog(response, dukeImage)
            );
        }
        userInput.clear();

        if (input.equals("bye")) {
            PauseTransition exitDelay = new PauseTransition(Duration.seconds(2));
            exitDelay.setOnFinished(event -> Platform.exit());
            exitDelay.play();
        }
    }
}
