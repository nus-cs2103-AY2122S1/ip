package duke;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import duke.ui.Ui;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 *
 * @author Zhi Bin
 * @version Duke Level 10
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Duke duke;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/shrek.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/pepe.png"));

    /**
     * The function to invoke once the Main has loaded. This shows
     * the greeting message and sets some property.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(Ui.showGreetingMessage(), dukeImage)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and
     * then appends them to the dialog container. Clears the user input after processing. If the
     * response is empty string, it means that user has entered bye command. The platform will
     * close after 5 seconds.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        if (!response.equals("")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            if (response.equals(Ui.showFarewellMessage())) {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.exit();
                        System.exit(0);
                    }
                }, 3000);
            }
        } else {
            assert response.equals("") : "Response should be empty because duke is not active anymore";
            dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        }

        userInput.clear();
    }
}
