package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.JPG"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));


    /**
     * method to initialize the program.
     */
    @FXML
    public void initialize() {
//        assert false;
        assert userImage != null;
        assert dukeImage != null;
        duke = new Duke();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        addDukeDialogBox(duke.getDukeBot().startBot());
        addDukeDialogBox(duke.getDukeBot().loadList("./data/list.txt"));
    }

    /**
     * Method to set the duke variable.
     * @param d the duke to be assigned to the class field.
     */
    public void setDuke(Duke d) {
        duke = d;

    }

    /**
     * Method to add a DialogBox for a new Duke message.
     * @param message the text of the message
     */
    public void addDukeDialogBox(String message) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(message, dukeImage)
        );
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
        userInput.clear();
    }
}
