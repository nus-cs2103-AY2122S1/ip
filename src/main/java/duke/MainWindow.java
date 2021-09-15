package duke;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.concurrent.TimeUnit;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane{
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        Image bgImage = new Image(this.getClass().getResourceAsStream("/images/betterDukeBackground.png"));
        BackgroundImage myBI= new BackgroundImage(bgImage,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        dialogContainer.setBackground(new Background(myBI));

    }

    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(duke.responseGenerator.greet(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String responseText = getResponse(userText);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getDukeDialog(responseText, dukeImage)
        );

        userInput.clear();

        try {
            if (responseText.equals("Bye. Hope to see you again soon!")) {
                TimeUnit time = TimeUnit.SECONDS;
                time.sleep(3);
                Platform.exit();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gets Duke's response as a String based on the input the user enters.
     *
     * @param input The input entered by the user.
     * @return Duke's response as a String.
     */
    public String getResponse(String input) {
        try {
            return duke.parser.parse(input, duke.taskList, duke.responseGenerator, duke.storage);
        } catch (InvalidCommandException e) {
            return e.getMessage();
        }
    }
}
