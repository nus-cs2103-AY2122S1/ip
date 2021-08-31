package duke;

import duke.controller.DialogBox;
import duke.exception.DukeException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Main extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;

    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeLogo = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Duke dukeChatbot = new Duke();


    @Override
    public void start(Stage stage) throws Exception {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox(10);
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();

        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        stage.setTitle("Your friendly Duke Chatbot");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);


        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnAction(e -> handleUserInput());
        userInput.setOnAction(e -> handleUserInput());

        Label openingMessage = new Label(dukeChatbot.openDukeChatBot());
        openingMessage.setWrapText(true);

        dialogContainer.getChildren().add(DialogBox.getDukeDialog(openingMessage, new ImageView(dukeLogo)));

        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = null;
        DialogBox fromDuke = null;
        try {
            dukeText = new Label(getResponse(userInput.getText()));
            fromDuke = DialogBox.getDukeDialog(dukeText, new ImageView(dukeLogo));
        } catch (DukeException e) {
            fromDuke.modifyColorForError();
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(dukeLogo)));
        userInput.clear();

    }

    private String getResponse(String input) {
        String toReturn = "";
        try {
            toReturn = dukeChatbot.getResponse(input);
        } catch (DukeException e) {
            toReturn = GUI.printErrorMessage(e);
            throw e;
        } finally {
            return toReturn;
        }

    }
}
