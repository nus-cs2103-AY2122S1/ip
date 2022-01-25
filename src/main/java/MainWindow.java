import java.util.Objects;

import duke.Duke;
import duke.HelpUi;
import duke.Ui;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
    @FXML
    private Button listButton;
    @FXML
    private Button clearButton;

    @FXML
    private ListView<String> taskList;

    private Duke duke;

    private final Image userImage = new Image(
        Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.jpg")));
    private final Image dukeImage = new Image(
        Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke.jpg")));

    /**
     * Starts up the main window with auto scrolling.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Set duke and loads up the saved list to display and display the welcome message.
     *
     * @param d the duke object to set
     */
    public void setDuke(Duke d) {
        duke = d;
        this.displayList();
        dialogContainer.getChildren().add(
            DialogBox.getDukeDialog("List loaded!\n" + Ui.welcomeMessage(), dukeImage));
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
        if (Objects.equals(input, "bye")) {
            Platform.exit();
        }
        this.displayList();
        userInput.clear();
    }

    /**
     * Refreshes the current task list to show the updated one.
     */
    @FXML
    private void displayList() {
        taskList.getItems().clear();
        String list = duke.displayList();
        taskList.getItems().addAll(list);
    }

    /**
     * Displays a help message for new users.
     */
    @FXML
    private void clearList() {
        dialogContainer.getChildren().clear();
    }
}
