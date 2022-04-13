package duke.ui;

import duke.Duke;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

// @@author kevinmingtarja-reused
// Reused from https://se-education.org/guides/tutorials/javaFxPart4.html
// with minor modifications
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/bot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        sendButton.setBackground(new Background(new BackgroundFill(Color.web("#06f"), null, null)));
        sendButton.setTextFill(Color.WHITE);

        userInput.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        userInput.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
    }

    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(duke.greet(), botImage));
        dialogContainer.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        dialogContainer.setPadding(new Insets(0, 4, 24, 4));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     *
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, botImage)
        );

        if (input.equals("bye")) {
            handleQuit();
        }

        userInput.clear();
    }

    /**
     * Quits application after three seconds
     */
    private void handleQuit() {
        PauseTransition timeout = new PauseTransition(Duration.seconds(3));
        timeout.setOnFinished(event -> Platform.exit());
        timeout.play();
    }
}
