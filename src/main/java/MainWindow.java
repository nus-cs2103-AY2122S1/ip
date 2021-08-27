import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/** The main window controller. */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    /** The Duke object. */
    private Duke duke;

    /** The user's avatar. */
    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.png")));

    /** The bot's avatar. */
    private final Image dukeImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/ainsley.png")));

    /** Initialises the window. */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("*booting up......*" + "\n" + "I'm the AinsleyBot v3.0!", dukeImage));
    }

    /** Mounts Duke. */
    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /** Handles the user's input. */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.process(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage));
        userInput.clear();

        if (duke.isExit()) {
            CompletableFuture.delayedExecutor(2, TimeUnit.SECONDS).execute(Platform::exit);
        }
    }
}
