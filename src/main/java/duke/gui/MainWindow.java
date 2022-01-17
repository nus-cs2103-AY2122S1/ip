package duke.gui;

import duke.main.Duke;
import duke.ui.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

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
    private BackgroundImage backgroundImage;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/RanRan.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/NaiLin.png"));


    private void setBackgroundImage() {
        BackgroundSize backgroundSize;

        backgroundSize = new BackgroundSize(400, 600,
                false, false, true, false);
        backgroundImage = new BackgroundImage(new Image("/images/Asoul.jpg"),
                null, null, null, backgroundSize);
    }

    private void isQuit(String input) {
        if (input.contains("bye")) {
            System.exit(0);
        }
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        this.setBackgroundImage();
        dialogContainer.setBackground(new Background(backgroundImage));
    }

    public void setDuke(Duke d) {
        duke = d;
        Ui textUi = new Ui();
        String greet = textUi.greet();
        dialogContainer
                .getChildren()
                .addAll(DialogBox
                        .getDukeDialog(greet, dukeImage));
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
        isQuit(input);
    }
}
