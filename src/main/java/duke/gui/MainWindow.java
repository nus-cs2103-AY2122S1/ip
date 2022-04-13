package duke.gui;

import duke.Duke;
import duke.data.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/PaperFace.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/CoffeeSip.png"));
    private Image bgImage = new Image(this.getClass().getResourceAsStream("/images/Wallpaper.jpg"));
    private Image sendIcon = new Image(this.getClass().getResourceAsStream("/images/Send.jpg"));
    private ImageView view = new ImageView(sendIcon);


    @FXML
    private void sendIntroMessage() {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(Ui.showWelcome(), dukeImage));
    }

    @FXML
    public void initialize() {

        //@@author erinmayg-reused
        //Reused from https://github.com/erinmayg/ip/blob/master/src/main/java/duke/gui/MainWindow.java (For initialize)
        //Reused from https://github.com/erinmayg/ip/blob/master/src/main/resources/view/scrollbar.css (For css)
        // with minor modifications
        // Background
        BackgroundSize bgSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage bg = new BackgroundImage(bgImage,
            BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
            BackgroundPosition.DEFAULT, bgSize);
        dialogContainer.setBackground(new Background(bg));

        // Scroll Pane and Scroll Bar
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.getStylesheets().add(this.getClass().getResource("/view/scrollbar.css").toString());

        // Button
        view.setFitHeight(25);
        view.setFitWidth(25);
        sendButton.setGraphic(view);
        //@@author

        sendIntroMessage();
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
        assert duke != null : "Duke should have been initialised first!";
        String input = userInput.getText();
        String response = duke.getResponse(input);
        //Does not show a visual response when a user enters nothing
        if (!input.isEmpty()) {
            dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
            );
        }

        // Referenced from https://github.com/zihaooo9/ip/commit/a9528849baeb23011b8225029e4ed8ea2b17235e
        // @@author CheyanneSim-reused
        if (response.contains(Ui.showGoodbye())) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
            pause.setOnFinished(event -> {
                Platform.exit();
            });
            pause.play();
        }
        //@@author
        userInput.clear();
    }
}