package duke.ui.controller;

import duke.Duke;
import duke.DukeException;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Duke duke;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Biscuit.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        DialogBox greet = DialogBox.getDukeDialog(duke.greet(), dukeImage);
        dialogContainer.getChildren().addAll(greet);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws DukeException {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        //@@author luffingluffy-reused
        // Closes the window if the "bye" input is given.
        // Reused from https://github.com/jiayushe/duke/blob/master/src/main/java/duke/MainWindow.java
        if (input.equals("bye")) {
            new Thread(() -> {
                try {
                    Thread.sleep(250);
                    System.exit(0);
                } catch (InterruptedException e) {
                    dialogContainer.getChildren().add(
                            DialogBox.getDukeDialog("Exiting!", dukeImage)
                    );
                }
            }).start();
        }
        //@@author
    }
}
