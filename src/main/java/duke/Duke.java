package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Objects;

/**
 * Contains main() method for Duke chatbot for IP of CS2103 2021.
 *
 * author: Ren Weilin A0216723J
 */
public class Duke extends Application {

    private final UI userInterface;
    private final TaskList taskList;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/spongebob.png")));
    private Image duke = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/patrick.png")));

    /**
     * Initializes the necessary components for Duke to function.
     */
    public Duke() {
        Parser parser = new Parser();
        taskList = new TaskList();
        Storage storage = new Storage(taskList);
        userInterface = new UI(parser, storage, taskList);
    }

//    /**
//     * Starts Duke chatbot.
//     *
//     * @param args CMD arguments.
//     */
//    public static void main(String[] args) {
//        Duke dukeInstance = new Duke();
//        try {
//            dukeInstance.userInterface.start();
//        } catch (DukeException e) {
//            System.out.println(e.getMessage());
//        } finally {
//            dukeInstance.userInterface.start();
//        }
//    }

    /**
     * Enters GUI start
     *
     * @param stage The stage for Duke UI.
     */
    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);


        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

//        try {
//            this.userInterface.start();
//        } catch (DukeException e) {
//            dialogContainer.getChildren().addAll(
//                    DialogBox.getUserDialog(new Label(e.getMessage()), new ImageView(duke)));
//        } finally {
//            this.userInterface.start();
//        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

}
