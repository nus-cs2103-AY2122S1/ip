package duke.gui;

import duke.Duke;
import duke.ui.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Image bgImage = new Image(this.getClass().getResourceAsStream("/images/DaBg.JPEG"));
    /**
     * initializes the gui.
     */
    @FXML
    public void initialize() {
        //@@author ChengJiyuqing-reused
        BackgroundImage bgImage = new BackgroundImage(this.bgImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        dialogContainer.setMaxHeight(Double.POSITIVE_INFINITY);
        Background background = new Background(bgImage);
        dialogContainer.setBackground(background);
        //@@author
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeMsg = Ui.getWelcomeMsg();
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(welcomeMsg, dukeImage));
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
        // @@author zihaooo9-reused
        if (response.contains("Bye. Hope to see you again soon!")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
            pause.setOnFinished(event -> {
                Platform.exit();
            });
            pause.play();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
