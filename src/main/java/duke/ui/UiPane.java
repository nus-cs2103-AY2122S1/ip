package duke.ui;

import java.util.List;

import duke.Duke;
import duke.task.Task;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * Represents the main UI panel for the application.
 */
public class UiPane extends AnchorPane {
    /* The object representing the main application class. Commands are sent back to this object to process. */
    private final Duke duke;
    /* The Label for the message in the UI */
    private Label messageLabel = new Label();
    /* The TextField to accept commands in the UI */
    private TextField commandField = new TextField();
    /* The Execute button in UI */
    private Button execButton = new Button("Execute");
    /* The TaskListPane in the UI */
    private TaskListPane taskListPane = new TaskListPane();

    /**
     * Constructs the UiPane for the application with the main application object.
     *
     * @param duke The main application object to send commands back to for processing.
     */
    public UiPane(Duke duke) {
        this.duke = duke;
        commandField.setPrefColumnCount(40);
        commandField.setPromptText("Enter your command here. Then, click Execute or press the Enter key.");
        HBox bottom = new HBox();
        bottom.getChildren().addAll(commandField, execButton);
        bottom.setSpacing(5);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(taskListPane);
        scrollPane.setMinViewportHeight(275);
        scrollPane.setFitToWidth(true);
        scrollPane.setMaxHeight(275);
        this.getChildren().addAll(scrollPane, messageLabel, bottom);
        AnchorPane.setTopAnchor(scrollPane, 10.0);
        AnchorPane.setLeftAnchor(scrollPane, 10.0);
        AnchorPane.setRightAnchor(scrollPane, 10.0);
        AnchorPane.setBottomAnchor(messageLabel, 40.0);
        AnchorPane.setLeftAnchor(messageLabel, 10.0);
        AnchorPane.setBottomAnchor(bottom, 10.0);
        AnchorPane.setLeftAnchor(bottom, 10.0);
        AnchorPane.setRightAnchor(bottom, 10.0);
        execButton.setOnAction((event -> sendCommand()));
        commandField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                sendCommand();
            }
        });
    }

    /**
     * Sends the commands input by the user back to the main application class for processing.
     */
    private void sendCommand() {
        String cmd = commandField.getText();
        commandField.setText("");
        duke.executeCommand(cmd);
    }

    /**
     * Shows the message to the user.
     *
     * @param message The message to show.
     */
    public void showMessage(String message) {
        messageLabel.setTextFill(Color.BLACK);
        messageLabel.setText(message);
    }

    /**
     * Shows the error message to the user.
     *
     * @param message The error message to show.
     */
    public void showErrorMessage(String message) {
        messageLabel.setTextFill(Color.RED);
        messageLabel.setText(message);
    }

    /**
     * Refreshes the TaskListPane by passing a new list of tasks for display.
     *
     * @param taskList The task list to be passed for display.
     */
    public void showTaskList(List<Task> taskList) {
        taskListPane.showTaskList(taskList);
    }

    /**
     * Sends the command to the main application to close.
     */
    public void close() {
        duke.closeApplication();
    }
}
