package banana;

import javafx.application.Platform;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


/**
 * Controller for MainWindow.
 * Provides the layout for the other controls.
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
    private Image user;
    private Image bot;

    @FXML
    public void initialize() {
        dialogContainer.setBackground(new Background(new BackgroundFill(
                Color.DARKSEAGREEN, null, null)));
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        try {
            user = new Image(new FileInputStream("/Users/ravi57004/ip/levi.png"));
            bot = new Image(new FileInputStream("/Users/ravi57004/ip/hange.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(
                        " Hello I'm Hange! \n How can I help you?", bot)
        );
        handleActions();
    }

    /**
     * Initialises the duke object.
     *
     * @param d the Duke object
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and
     * the other containing Duke's reply and then appends
     * them to the dialog container. Clears the user input
     * after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("bye")) {
            Platform.exit();
        } else {
            String response = duke.getResponse(input);
            Parser.setPrevInput(input);
            System.out.println(response);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input + " ",
                            user),
                    DialogBox.getDukeDialog(response, bot)
            );
        }
        userInput.clear();
    }

    /**
     * Allows the send button and textfield
     * to handle user input.
     */
    public void handleActions() {
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }
}