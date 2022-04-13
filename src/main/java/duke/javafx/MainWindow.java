package duke.javafx;

import java.util.Objects;

import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;

/**
 * Controller for javafx.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Duke duke;

    private Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/SmolAnkaa.png")));
    private Image dukeImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/Apollo.jpg")));
    private Image bgImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/Background.png")));

    /**
     * Initialises the MainWindow
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.setBackground(new Background(new BackgroundImage(this.bgImage, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Hello, welcome to Duke!", dukeImage)
        );
    }

    /**
     * Sets the local variable of class Duke to the instance of Duke
     * @param d the current instance of Duke
     */
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
        dialogContainer.setFillWidth(true);
        userInput.clear();
        if (duke.isDukeExit()) {
            Platform.exit();
        }
    }
}
