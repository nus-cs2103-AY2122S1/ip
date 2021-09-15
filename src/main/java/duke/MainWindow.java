package duke;

import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends Stage {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/soyBoy.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/yesChad.png"));
    private Image chatBackgroundImage =
            new Image(this.getClass().getResourceAsStream("/images/chatBackground.jpg"));

    private FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MainWindow.fxml"));

    /**
     * Constructor for MainWindow.
     */
    public MainWindow() {
        try {
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets up javafx components after this MainWindow constructor is called.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Duke object to be used by the application.
     *
     * @param d Duke object.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * If user inputs bye, begin delay of 1 second to allow Duke to say Goodbye before closing application.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        // Only create dialog is user input is not whitespace(s) or null.
        if (!input.isBlank()) {
            DukeResponse dukeResponse = duke.getResponse(input);
            String response = dukeResponse.getResponse();

            // Allow Duke to say goodbye to the user before closing application.
            if (response.equals(duke.getUi().getGoodbyeMessage())) {
                PauseTransition delay = new PauseTransition(Duration.seconds(1));
                delay.setOnFinished(event -> Platform.exit());
                delay.play();
            }

            DialogBox userDialogBox = DialogBox.getUserDialog(input, userImage);
            // Check is Duke Response indicates an error. If so, use an Error DialogBox instead.
            DialogBox dukeDialogBox;
            if (dukeResponse.isError()) {
                dukeDialogBox = DialogBox.getErrorDialog(response, dukeImage);
            } else {
                dukeDialogBox = DialogBox.getDukeDialog(response, dukeImage);
            }

            dialogContainer.getChildren().addAll(
                    userDialogBox,
                    dukeDialogBox
            );
        }

        userInput.clear();
    }

    @FXML
    private void handleUserWelcome() {
        dialogContainer.getChildren().add(DialogBox.getDukeWelcome(dukeImage));
    }

    @FXML
    private void handleUserBye() {
        dialogContainer.getChildren().add(DialogBox.getDukeBye(dukeImage));
    }

}
