package workdone.ui;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import workdone.WorkDone;

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

    private WorkDone workDone;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image workDoneImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setWorkDone(WorkDone d) {
        workDone = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing WorkDone's reply and then
     * appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = workDone.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                response.contains("☹")
                        ? DialogBox.getErrorDialog(response, workDoneImage)
                        : DialogBox.getWorkDoneDialog(response, workDoneImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            // exit the program after 1s
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    Platform.exit();
                    timer.cancel();
                }
            };
            timer.schedule(task, 1000);
        }
    }

    /**
     * Greets the user by displaying the greeting message when the gui starts running.
     */
    public void greetTheUser() {
        String greetingMessage = workDone.getGreetingMessage();
        dialogContainer.getChildren().add(
                DialogBox.getWorkDoneDialog(greetingMessage, workDoneImage)
        );
    }
}
