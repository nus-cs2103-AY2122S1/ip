package duke.ui;

import duke.Duke;
import duke.Main;
import duke.Ui;
import duke.utils.Record;
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
    private Main main;

    private Image userImage;
    private Image dukeImage;

    @FXML
    public void initialize() {
        userImage = new Image(this.getClass().getResourceAsStream("/duke/images/You.gif"));
        dukeImage = new Image(this.getClass().getResourceAsStream("/duke/images/Duke.gif"));
        assert userImage != null && dukeImage != null;
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDukeAndMain(Duke duke, Main main) {
        this.duke = duke;
        this.main = main;
        this.handleInput("greet");
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        handleInput(input);
    }
    
    @FXML
    private void handleInput(String input) {
        Record r = duke.getResponse(input);
        String response = r.msg();
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage));
        userInput.clear();
        if (r.bye()) {
            try {
                main.stop();
            } catch (Exception e) {
                Ui.reply("Error in quitting Duke.");
            }
        }
    }
}
