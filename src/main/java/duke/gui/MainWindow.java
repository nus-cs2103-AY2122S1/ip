package duke.gui;

import duke.Duke;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.application.Platform;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/RealUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/RealDuke.png"));

    /**
     * Initialises the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String[] content = Ui.printIntro();
        String[] listContent = TaskList.printList("list");
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(String.join("\n", content), dukeImage),
                DialogBox.getDukeDialog(String.join("\n", listContent), dukeImage)
        );

    }

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
        String[] response = duke.getResponse(input);
        if (response[0] == "Bye. Hope to see you again soon!") {
            Platform.exit();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(String.join("\n", response), dukeImage)
        );
        userInput.clear();
    }
}
