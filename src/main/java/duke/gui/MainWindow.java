package duke.gui;

import duke.Duke;
import duke.exceptions.DukeExitException;

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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/userProfilePic.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/dukeProfilePic.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        displayWelcomeDialog();
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
        try {
            String response = duke.getResponse(input);
            makeDialogBox(input, response);
        } catch (DukeExitException e) {
            String response = "Bye bye!";
            makeDialogBox(input, response);
            //TODO: Add a delay here so this message is visible
            System.exit(1);
        }

        userInput.clear();
    }

    /**
     * Format user dialog and dike response
     *
     * @param input user input String
     * @param response duke response String
     */
    private void makeDialogBox(String input, String response) {
        DialogBox userBox = DialogBox.getUserDialog("User", input, userImage);
        DialogBox dukeBox = DialogBox.getDukeDialog("Duke", response, dukeImage);
        dialogContainer.getChildren().addAll(userBox, dukeBox);
    }

    private void displayWelcomeDialog (){
        String welcome_message = "Welcome to Duke\nType list to see all current tasks\nType help for help";
        DialogBox dukeBox = DialogBox.getDukeDialog("Duke", welcome_message, dukeImage);
        dialogContainer.getChildren().addAll(dukeBox);
    }





}
