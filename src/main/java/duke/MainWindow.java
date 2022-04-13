package duke;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Cat.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/ROTS.png"));
    private Image mountainsPic = new Image(this.getClass().getResourceAsStream("/images/Mountains.png"));

    /**
     * Initialises the GUI and shows Welcome Message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        BackgroundSize bkgSize = new BackgroundSize(552, 388, true, true, false, true);
        BackgroundImage bkgImg = new BackgroundImage(mountainsPic, BackgroundRepeat.NO_REPEAT, null,
                BackgroundPosition.CENTER, bkgSize);
        Background bkg = new Background(bkgImg);
        dialogContainer.setBackground(bkg);

        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Duke.getCurrDuke().getWelcomeMessage(), dukeImage)
        );
    }

    /**
     * Sets the current instance of Duke.
     *
     * @param d the Duke Instance to set.
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

        if (input.equals("bye")) {
            Platform.exit();
        } else {

            duke.acceptUserInput(input);
            String response = duke.flushUiBuffer();

            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            userInput.clear();
        }
    }
}
