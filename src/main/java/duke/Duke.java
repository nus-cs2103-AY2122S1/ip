package duke;

import java.io.IOException;
import java.util.*;

import com.sun.scenario.effect.DropShadow;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Duke extends Application {
    private TaskList taskList;
    private UserInterface userInterface;
    private Storage storage;
    private Parser parser;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private void handleUserInput(TextField userInput, VBox dialogContainer) {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.setPadding(new Insets(10));

        Circle clip = new Circle(40,40,20);
        Circle clip2 = new Circle(30,30,20);

        ImageView userImageView = new ImageView(user);
        userImageView.setClip(clip);

        ImageView dukeImageView = new ImageView(duke);
        dukeImageView.setClip(clip2);

        DialogBox userDialog = DialogBox.getUserDialog(userText, userImageView);
        DialogBox dukeDialog = DialogBox.getDukeDialog(dukeText, dukeImageView);
        userDialog.setSpacing(30);
        dukeDialog.setSpacing(30);

        dialogContainer.getChildren().addAll(
            userDialog,
            dukeDialog
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        TextField userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

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

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(userInput, dialogContainer);
        });

        userInput.setOnAction((event) -> {
            handleUserInput(userInput, dialogContainer);
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     *
     * @param filePath
     */
    public Duke(String filePath) {
        userInterface = new UserInterface();
        storage = new Storage(filePath);

        try {
            taskList = new TaskList(storage.load());
            userInterface = new UserInterface(taskList);
            parser = new Parser(taskList, userInterface, storage);
        } catch (IOException e) {
            taskList = new TaskList();
            userInterface.showLoadingError();
            userInterface = new UserInterface(taskList);
            parser = new Parser(taskList, userInterface, storage);
        }
    }

    public Duke() {

    }

    /**
     *
     */
    public void run() {
        userInterface.greet();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine().trim();
            try {
                parser.parse(input);
            } catch (InvalidCommandException | MissingToDoDescriptionException |
                    MissingDeadlineDescriptionException | MissingEventDescriptionException e) {
                System.out.println(e.getMessage());
            }

            if (input.equals("bye")) break;
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        String s = System.getProperty("user.dir");
        new Duke(s+ "/data/duke.txt").run();
    }
}
