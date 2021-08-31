package duke.gui;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    private Stage stage;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Initializes duke and welcome message when Gui opens.
     *
     * @param d
     * @param stage
     */
    public void setDuke(Duke d, Stage stage) {
        this.duke = d;
        this.stage = stage;
        dialogContainer.getChildren().addAll(d.welcomeUser());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.split(" ")[0].equals("bye")) {
            duke.saveTask();
            Platform.exit();
        } else {
            duke.getResponse(input, this.dialogContainer);
            userInput.clear();
        }
    }
}
