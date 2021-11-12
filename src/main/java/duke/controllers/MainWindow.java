package duke.controllers;

import duke.DataHandlerLayer;
import duke.Duke;
import duke.Logic;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for duke.controllers.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static String startUpText = "Oh my..... Looks like u have an old scroll.." +
            "\n \n Enter help command for any assistance you may need";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Kobold.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Croc.png"));

    /**
     * init the GUI for dukebot
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        Logic.preload();
        String text = startUpText + "\n" + DataHandlerLayer.getFilteredLog(a -> true);
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(text, dukeImage)
        );
    }

    /**
     * Set duke bot to use
     * @param d
     */
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
        String response = null;
        String temp = Logic.checkIfSpecialCommand(input);

        if (temp == null) {
            response = "There are no task my dear summoner <3";
        } else {
            response = temp;
        }
        System.out.println(response);
        if (input.contains("help")) {
            dialogContainer.getChildren().clear();
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        }
        userInput.clear();
    }
}
