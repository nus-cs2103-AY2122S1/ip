package duck;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
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

    private Duck duck;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/chicken.png"));
    private final Image duckImage = new Image(this.getClass().getResourceAsStream("/images/duck.png"));

    /**
     * Initialises GUI window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.setBackground(new Background(
                new BackgroundFill(Color.FLORALWHITE, CornerRadii.EMPTY, Insets.EMPTY))
        );
    }

    public void setDuck(Duck d) {
        duck = d;
        Ui ui = new Ui();
        dialogContainer.getChildren().addAll(
                DialogBox.getDuckDefaultDialog(ui.showWelcome(), duckImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duck's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duck.getResponse(userInput.getText());
        boolean isExceptionThrown = response.split("\\s+", 2)[0].equals("QUACK!!!");
        if (isExceptionThrown) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDuckExceptionDialog(response, duckImage)
            );
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDuckDefaultDialog(response, duckImage)
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
