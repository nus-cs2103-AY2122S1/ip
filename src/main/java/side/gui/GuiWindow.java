package side.gui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import side.Side;
import side.util.TaskList;
import side.util.Ui;

/**
 * Encapsulates the window that is shown to user when running the application.
 *
 * @author Lua Yi Da
 */

public class GuiWindow extends AnchorPane {

    private TaskList taskList;
    private Ui ui;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private final Image sideImage = new Image(this.getClass().getResourceAsStream("/images/side.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    @FXML
    private void handleUserInput() {
        Side side = new Side();
        String input = userInput.getText();
        String response = side.replyToCommand(userInput.getText(), taskList);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage),
                DialogBox.getSideDialog(response, sideImage));

        //pauses program for 1 second upon bye
        if (userInput.getText().stripTrailing().equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }

        userInput.clear();
    }

    /**
     * Retrieves tasks from previous session.
     *
     * @param tasks Tasklist to be retrieved.
     */
    public void setTaskList(TaskList tasks) {
        this.taskList = tasks;
        this.ui = new Ui();

        String response = ui.getGreeting() + "\n" + tasks.retrieve();
        dialogContainer.getChildren().addAll(DialogBox.getSideDialog(response, sideImage));
    }
}

