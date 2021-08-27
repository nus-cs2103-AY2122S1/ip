import java.util.Objects;

import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.png")));

    private Image dukeImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/ainsley.png")));

    /** Initialises the window*/
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("*booting up......*" + "\n" + "I'm the AinsleyBot v3.0!", dukeImage));
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.process(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage));
        userInput.clear();

        if (duke.isExit()) {
            exit();
        }
    }

    private void exit() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            Platform.exit();
        }
    }
}
