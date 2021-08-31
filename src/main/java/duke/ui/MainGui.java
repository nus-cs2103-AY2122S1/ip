package duke.ui;

import java.util.Objects;

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

    private Image user = new Image(
        Objects.requireNonNull(this.getClass().getResourceAsStream("/images/doge_circle.png")));
    private Image duke = new Image(
        Objects.requireNonNull(this.getClass().getResourceAsStream("/images/gigachad.jpg")));

    @Override
    public void start(Stage stage) {
        // sets the default css

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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        stage.setScene(scene);
        stage.show();

        sendButton.setOnMouseClicked((event) -> handleUserInput());

        userInput.setOnAction((event) -> handleUserInput());
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        class EmptySpace extends Region {
            Region getEmptySpace() {
                this.setMinHeight(10);
                return this;
            }
        }
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userText, user),
            new EmptySpace().getEmptySpace(),
            DialogBox.getDukeDialog(dukeText, duke),
            new EmptySpace().getEmptySpace()
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
