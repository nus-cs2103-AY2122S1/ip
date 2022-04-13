package duke;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

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

    private static final String INTRO = "Yo! As you might know, I'm Michael Scott. "
            + "The name of my bot version is Duke, "
            + "cause my position is pretty much like the Duke - "
            + "ME A BOSS! At least THAT'S WHAT SHE SAID";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke = new Duke();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpeg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initialises the GUI.
     * @throws DukeException exception that is thrown.
     * @throws IOException exception that is thrown.
     */
    @FXML
    public void initialize() throws DukeException, IOException {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        intro();
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws DukeException, IOException {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        if (input.trim().equals("bye")) {
            new Timer().schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            Platform.exit();
                            System.exit(0);
                        }
                    }, 1500);
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    @FXML
    private void intro() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(INTRO, dukeImage));
    }
}
