package duke;

import java.util.Timer;
import java.util.TimerTask;

import duke.exception.DukeException;
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

    private static final long SECOND_IN_MILLISECONDS = 1000;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/PusheenUser200.png"));
    private Image happyBotImage = new Image(this.getClass().getResourceAsStream("/images/PusheenBot200.png"));
    private Image angryBotImage = new Image(this.getClass().getResourceAsStream("/images/PusheenAngry200.png"));
    private Image sleepBotImage = new Image(this.getClass().getResourceAsStream("/images/PusheenSleep200.png"));


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    @FXML
    public void setDuke(Duke d) {
        duke = d;
        String startMessage;
        try {
            startMessage = duke.handleStart();
        } catch (DukeException e) {
            startMessage = e.getMessage();
        }
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(startMessage, happyBotImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Response response = duke.handleCommands(input);
        String responseOutput = response.getMessage();

        Image botImage = response.hasError() ? angryBotImage : happyBotImage;
        botImage = response.isTerminate() ? sleepBotImage : botImage;
        boolean isTerminate = response.isTerminate();


        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(responseOutput, botImage));
        userInput.clear();

        if (isTerminate) {
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                }
            };
            timer.schedule(task, SECOND_IN_MILLISECONDS * 3);
        }
    }
}
