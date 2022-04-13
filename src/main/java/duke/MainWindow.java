package duke;

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

    private Main main;
    private Duke duke;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setMain(Main d, Duke duke) {
        main = d;
        this.duke = duke;
        this.duke.setMainWindow(this);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
        );
        main.getResponse(input);
        userInput.clear();
    }

    /**
     * Gives response to user input.
     *
     * @param s input from the user
     */
    public void sendDukeResponse(String s) {
        if (!s.equals("")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(s, dukeImage)
            );
        }
    }

    /**
     * Adds a warning object to dialog.
     *
     * @param s String to be printed to user.
     */
    public void sendDukeWarning(String s) {
        if (s.equals("")) {
            return;
        }
        dialogContainer.getChildren().addAll(
                Warning.getDukeWarning(s)
        );
    }
}
