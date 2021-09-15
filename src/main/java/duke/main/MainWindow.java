package duke.main;

import duke.exception.DukeException;
import duke.util.Ui;

import javafx.animation.PauseTransition;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

import javafx.util.Duration;

/**
 * Encapsulates the controller for MainWindow.
 */
public class MainWindow extends AnchorPane {
    //@@author Shruthi0310 -reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html
    //with minor modifications
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
    private Ui ui = new Ui();
    private static boolean hasShownMenu = false;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Picture1.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Picture2.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void initialDialog() {
        if (!hasShownMenu) {
            String response = ui.showWelcome();
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            hasShownMenu = true;
        }
    }

    @FXML
    private void handleUserInput() throws DukeException {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        duke.save();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        handleBye(response);
    }

    @FXML
    private void handleBye(String response) {
        String byeMessage = "Bye! Hope to see you again soon!";
        if(response.equals(byeMessage)) {
            PauseTransition transition = new PauseTransition(Duration.seconds(1));
            transition.setOnFinished(e -> stage.close());
            transition.play();
        }
    }
}