package duke.ui;

import java.io.IOException;
import java.util.Objects;

import duke.Duke;
import duke.DukeException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The main graphical user interface for the duke application.
 */
public class MainGui extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image userImg = new Image(
        Objects.requireNonNull(this.getClass().getResourceAsStream("/images/doge_circle.png")));
    private Image dukeImg = new Image(
        Objects.requireNonNull(this.getClass().getResourceAsStream("/images/gigachad.jpg")));

    private Duke duke;
    private Ui ui; // For getting output messages
    // 0 -> 1 (with name) -> 2 (with file location) -> 3 (with task list size)
    private int sequence = 0; // Checks to see if the user inputs their name, file location, and task list size.
    private String filePath; // To temporarily store the file path to create duke.

    @Override
    public void start(Stage stage) {

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinWidth(500);
        stage.setMinHeight(600);

        mainLayout.setPrefSize(500, 600);

        scrollPane.setPrefSize(500, 555);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-font-size: 18px;"); // widens the scroll bar
        dialogContainer.setStyle("-fx-font-size: 14px;"); // resets the font size

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) ->
            scrollPane.setVvalue(1.0)); // always scroll to the bottom automatically

        userInput.setPrefWidth(425.0);
        userInput.setPrefHeight(40.0);

        sendButton.setPrefWidth(55.0);
        sendButton.setPrefHeight(40.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(
                new Label("Hi, I am Duke, what is your name?"), dukeImg),
            new EmptySpace().getEmptySpace());

        stage.setScene(scene);
        stage.show();

        sendButton.setOnMouseClicked((event) -> handleUserInput());

        userInput.setOnAction((event) -> handleUserInput());
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText;
        switch (sequence) {
        case 0: // start
            ui = new Ui(userInput.getText());
            sequence++;
            dukeText = new Label(
                String.format("Hi, %s! That is a nice name. Now, where would you want to store your duke data files?",
                    userInput.getText()));
            break;
        case 1: // get file location
            filePath = userInput.getText();
            sequence++; // We can't create duke here yet! We need the max size
            dukeText = new Label("Ok, so what should the maximum size of the list be?");
            break;
        case 2:
            try {
                duke = new Duke(filePath, Integer.parseInt(userInput.getText()));
                dukeText = new Label("Great! You are all set! How can I help you today?");
                sequence++;
            } catch (NumberFormatException e) {
                dukeText = new Label("This is not a whole number. Please enter something sensible!");
            } catch (IOException e) {
                dukeText = new Label(e.getMessage());
                sequence--; // Go back one step
            }
            break;
        case 3:
            try {
                dukeText = new Label(getResponse(userInput.getText()));
            } catch (DukeException e) {
                dukeText = new Label(e.getMessage());
            }
            break;
        default:
            dukeText = new Label("Something went very wrong!");
        }

        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userText, userImg),
            new EmptySpace().getEmptySpace(),
            DialogBox.getDukeDialog(dukeText, dukeImg),
            new EmptySpace().getEmptySpace()
        );
        userInput.clear();
        if (ui.willExit()) {
            try {
                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String getResponse(String input) {
        return ui.checkInput(input, duke);
    }

    private static class EmptySpace extends Region {
        Region getEmptySpace() {
            this.setMinHeight(10);
            return this;
        }
    }
}
