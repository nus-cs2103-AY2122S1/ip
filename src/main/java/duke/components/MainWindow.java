package duke.components;

import java.util.Timer;
import java.util.TimerTask;

import duke.Duke;
import duke.command.HelpCommand;
import duke.commandresult.CommandResult;
import duke.exception.DukeException;
import duke.tasklist.TaskList;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * The main window where Duke interaction occurs.
 */
public class MainWindow extends AnchorPane {

    public static final String FXML_STRING_PATH = "/view/MainWindow.fxml";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Hello! Welcome to Duke!", dukeImage)
        );
        dialogContainer.getChildren().addAll(
                getDialogBoxWithHelpCommand()
        );
    }

    private DialogBox getDialogBoxWithHelpCommand() {
        HelpCommand helpCommand = new HelpCommand(new TaskList());
        CommandResult executedHelpCommand = helpCommand.execute();
        return DialogBox.getDukeDialog(executedHelpCommand.getFeedbackResult(), dukeImage);
    }

    /**
     * Sets the instance of duke to one provided.
     *
     * @param d The duke instance.
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
        CommandResult dukeResponse;
        try {
            dukeResponse = duke.getResponse(input);
            runDialogContainer(input, dukeResponse);
            if (dukeResponse.isExit()) {
                runExitDialogContainer();
            }
        } catch (DukeException e) {
            runDialogContainer(input, e.getMessage());
        }
        userInput.clear();
    }

    private void runDialogContainer(String input, CommandResult commandResult) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(commandResult.getFeedbackResult(), dukeImage)
        );
    }

    private void runDialogContainer(String input, String dukeInput) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(dukeInput, dukeImage)
        );
    }

    private void runExitDialogContainer() throws DukeException {
        duke.save();
        runDialogContainer("", "Bye. Hope to see you again soon!");
        schedulePlatformExitTimer();
        exitSystem();
    }

    private void schedulePlatformExitTimer() {
        new Timer().schedule(new TimerTask() {
            public void run () {
                Platform.exit();
            }
        }, 1000);
    }

    private void exitSystem() {
        System.exit(0);
    }
}
