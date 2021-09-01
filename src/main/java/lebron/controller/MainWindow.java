package lebron.controller;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import lebron.DialogBox;
import lebron.Lebron;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

//Solution below adapted from https://se-education.org/guides/tutorials/javaFx.html
//Credit to Jeffry Lum
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

    private Lebron lebron;

    private Image lebronImage = new Image(this.getClass().getResourceAsStream("/images/lebron.jpg"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/blank.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(new Label("    Hello! I'm LebronChatBot\n"
                                + "    What can I do for you?\n"), new ImageView(lebronImage))
        );
    }

    public void setLebron(Lebron l) {
        lebron = l;
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        Label userText = new Label(userInput.getText());
        Label lebronText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userImage)),
                DialogBox.getDukeDialog(lebronText, new ImageView(lebronImage))
        );
        userInput.clear();
    }

    @FXML
    private String getResponse(String input) throws IOException {
        return lebron.run(input);
    }
}
