package duke.graphics;

import duke.Duke;
import duke.user.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

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
    @FXML
    private Button helpButton;
    @FXML
    private Popup helpPopup;


    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Lunch-min.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Dinner.jpg"));

    /**
     * Runs when the MainWindow is initialized.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        sendIntroMessage();

    }

    public void setDuke(Duke d) {
        duke = d;
    }

    @FXML
    private void sendIntroMessage() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Hello! I'm Duke!\nYour friendly task manager!", dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {

        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        // @@author CheyanneSim-reused
        if (response.equals(Ui.displayByeMessage())) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
            pause.setOnFinished(event -> {
                Platform.exit();
            });
            pause.play();

        }
        userInput.clear();
    }

    @FXML
    private void openHelpPopup() {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/HelpWindow.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Command List");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        //HelpPopup.display();
    }
}
