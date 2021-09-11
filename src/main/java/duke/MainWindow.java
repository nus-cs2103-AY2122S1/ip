package duke;

import java.util.Timer;

import duke.ui.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 *
 * @author Cheong Yee Ming
 * @version Duke Level-10
 */
public class MainWindow extends AnchorPane {
    private final Ui ui;
    private final ExitProgram exitProgram;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Duke duke;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private final int seconds;

    /**
     * Constructor for MainWindow
     */
    public MainWindow() {
        ui = new Ui();
        seconds = 2;
        exitProgram = new ExitProgram();
    }
    /**
     * Method called upon starting of Duke.
     * Sets height of scroll panel and
     * sends greeting message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(ui.guiGreet(), dukeImage)
        );
    }

    /**
     * Sets Duke to new Duke.
     *
     * @param d The new Duke to be set.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the
     * other containing Duke's reply and then appends them to
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
        if (response.equals("Bye. Hope to see you again soon!")) {
            new Timer().schedule(exitProgram, seconds * 1000);
        }
        userInput.clear();
    }
}
