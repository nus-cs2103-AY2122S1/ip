package aisu.gui;

import aisu.Aisu;
import aisu.Storage;
import aisu.TaskList;
import aisu.Ui;
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
    // The @FXML annotation marks a private or protected member and makes it accessible to FXML despite its modifier.
    // Without the annotation, we will have to make everything public and expose our UI to unwanted changes.
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Aisu aisu;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/you.png"));
    private Image aisuImage = new Image(this.getClass().getResourceAsStream("/images/bot.gif"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setAisu(Aisu a) {
        aisu = a;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = aisu.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAisuDialog(response, aisuImage)
        );
        userInput.clear();
    }
}
