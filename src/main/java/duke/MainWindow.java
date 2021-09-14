package duke;

import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Duke duke;

    @FXML
    private ImageView imageView = new ImageView();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.gif"));


    /**
     * Initialises the chat bot.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        Ui ui = new Ui();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(ui.getWelcomeMsg(), dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    private Image getDukeImage(String dukeResponse) {
        String imagePath;
        if (dukeResponse.startsWith("OOPS!!! I'm sorry")) {
            imagePath = "/images/DaDukeConfused.gif";
        } else if (dukeResponse.startsWith("OOPS")) {
            imagePath = "/images/DaDukeOops.gif";
        } else if (dukeResponse.startsWith("See you")) {
            imagePath = "/images/DaDukeBye.gif";
        } else if (dukeResponse.startsWith("...")) {
            imagePath = "/images/pigeon-fluffy.gif";
        } else {
            imagePath = "/images/DaDuke.gif";
        }
        return new Image(this.getClass().getResourceAsStream(imagePath));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        Ui ui = new Ui();
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dukeImage = getDukeImage(response);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (response.equals(ui.getGoodbyeMsg())) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                }
            }, 2900);
        }
    }
}
