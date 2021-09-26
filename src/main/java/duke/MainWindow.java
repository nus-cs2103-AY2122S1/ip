package duke;

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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user-chatbot.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/dory-chatbot.png"));

    /**
     * Shows introduction paragraph and instructions to user.
     */
    public String showIntro() {
        String dory = "              \uD83E\uDD91 \uD83D\uDC1F \uD83D\uDC0B \uD83D\uDC19 \uD83D\uDC21 \uD83C\uDF0A "
                + "\n"
                + "refer to 'tinyurl.com/doryguide' for the bot's user guide! ";

        String fish = "                                          ....\n"
                + "                                         /.... \\\n"
                + "   hi my name is dory   .-`\\ \\      `...')\n"
                + "   and i can help you  ( o   | |          (\n"
                + "    remember things    `-_ / /_./``-._)\n"
                + "                                       `\\___\\";
        return fish + "\n" + dory;
    }

    /**
     *  Starts the Dialog box.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showFirstMessage();
    }

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
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Shows the intro message to user.
     */
    @FXML
    private void showFirstMessage() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(showIntro(), dukeImage));
    }


}
