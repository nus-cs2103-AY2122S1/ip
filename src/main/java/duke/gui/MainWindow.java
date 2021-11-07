package duke.gui;

import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Initializes duke and welcome message when Gui opens.
     *
     * @param d A duke instance.
     */
    public void setDuke(Duke d) {
        this.duke = d;
        this.duke.setDialogContainer(this.dialogContainer);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String commandType = input.split(" ")[0].trim();
        boolean isExit = commandType.equals("bye") || commandType.equals("bb");
        if (isExit) {
            duke.saveTask();
            Platform.exit();
        } else {
            duke.getResponse(input);
            userInput.clear();
        }
    }
}
