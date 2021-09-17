package blitz;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    private boolean flag = false;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Blitz blitz;

    private Image userImage = new Image(this.getClass()
            .getResourceAsStream("/images/hamster.png"));
    private Image blitzImage = new Image(this.getClass()
            .getResourceAsStream("/images/penguin.png"));

    /**
     * Initializes main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty()
                .bind(dialogContainer.heightProperty());
    }

    public void setBlitz(Blitz d) {
        blitz = d;
        String listOfTasks = "";
        try {
            listOfTasks = blitz.getTasks().listToString(blitz.getUi().getGreetingMessage(), blitz.getUi());
        } catch (BlitzException ex) {
            listOfTasks = ex.toString();
        }

        assert(listOfTasks.equals(new BlitzException("\"No items currently in the list!!\"")));

        String openingMessage = listOfTasks + "\n" + blitz.getUi().getFeatureList();
        dialogContainer.getChildren().addAll(
                DialogBox.getBlitzDialog(openingMessage, blitzImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     *     Blitz's reply and then appends them to the dialog container. Clears
     *     the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        if (flag) {
            Platform.exit();
        }
        String input = userInput.getText();
        if (input.equals("bye")) {
            try {
                blitz.getStorage().saveTasksInFile(blitz.getTasks());
            } catch (IOException e) {
                System.err.println(e);
            }
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getBlitzDialog(blitz.getUi().getGoodbyeMessage(), blitzImage)
            );
            assert(!flag);
            flag = true;

        } else {
            String response = Parser.parseCommand(input, blitz.getTasks(), blitz.getUi());
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getBlitzDialog(response, blitzImage)
            );
        }
        userInput.clear();
    }
}
