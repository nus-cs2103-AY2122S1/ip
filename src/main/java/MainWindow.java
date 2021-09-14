import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.jpg"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private HelpWindow helpWindow;

    private Stage primaryStage;

    private Duke duke;

    /**
     * Initializes the main window with scroll pane and dialog boxes.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
            DialogBox.getDukeDialog("Hello I'm Duke :) What do you want to do today?", dukeImage));
        helpWindow = new HelpWindow();
    }

    /**
     * Sets the duke object.
     *
     * @param d Duke object.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Sets the primary stage object.
     *
     * @param s Stage object.
     */
    public void setPrimaryStage(Stage s) {
        primaryStage = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        String response = duke.getResponse(input);
        Boolean isGuiResponse = false;
        if (response.startsWith(":")) {
            isGuiResponse = handleGuiResponse(response);
        }
        if (!isGuiResponse) {
            dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage));
        }
        userInput.clear();

        if (duke.hasExit()) {
            primaryStage.hide();
            helpWindow.hide();
        }
    }

    private boolean handleGuiResponse(String res) {
        if (res.equals(":help")) {
            handleHelp();
            return true;
        }
        return false;
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }
}
