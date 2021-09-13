import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Timer;
import java.util.TimerTask;


public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private ArchDuke duke;
    private boolean isExit = false;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.jpeg"));

    /**
     * Initialises MainWindow page.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(showWelcome(), dukeImage));
    }

    public void setDuke(ArchDuke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */

    @FXML
    private void handleUserInput() {
        if (isExit) {
            return;
        }
        String input = userInput.getText();
        String response = duke.getResponse(input);
        String[] dukeResponse = response.split(" ");
        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        // checks if error message thrown
        DialogBox dukeDialog;
        if (dukeResponse[0].equals("☹")) {
            dukeDialog = DialogBox.getErrorDialog(response, dukeImage);
        } else {
            dukeDialog = DialogBox.getDukeDialog(response, dukeImage);
        }
        dialogContainer.getChildren().addAll(
            userDialog,
            dukeDialog
        );
        userInput.clear();

        // printe bye message and close app
        if (response.equals("Bye. Hope to see you again soon!")) {
            isExit = true;
            exit();
        }

    }

    private void exit() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
                System.exit(0);
            }
        }, 2000);
    }

    /**
     * generates welcome message when app is loaded.
     *
     * @return Weclome message.
     */
    public String showWelcome() {
        String name = "ARCHDUKE!";
        return "Hello! I'm " + name + "\nHow can I help?";
    }
}
