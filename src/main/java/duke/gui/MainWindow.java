//@@author @fyshhh-reused
package duke.gui;

import duke.Duke;
import duke.ui.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Image bgImage = new Image(this.getClass().getResourceAsStream("/images/DaBg.JPEG"));

    /**
     * Initializes the GUI.
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
        String welcomeMessage = Ui.greet();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeMessage, new ImageView(dukeImage)));
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
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
                DialogBox.getUserDialog(input, new ImageView(userImage)),
                DialogBox.getDukeDialog(response, new ImageView(dukeImage))
        );
        userInput.clear();
    }
}
// @@author
