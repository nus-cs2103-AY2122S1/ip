package duke;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the Duke Bot.
 * Entry point to the programme.
 */
public class Duke extends Application {
    public static final String QUIT_COMMAND = "bye";
    private List todoList;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/Jiahao.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Ben.jpg"));

    /**
     * Starts to the program
     *
     * @param stage the stage to be displayed.
     */
    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html
    // with minor modifications
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
        scene.getRoot().setStyle("-fx-font-family: 'Courier New'");
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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        this.addDuke();
    }

    /**
     * Adds a Duke object after initial GUI setups.
     */
    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html
    // with minor modifications
    public void addDuke() {
        Duke dukeBot = new Duke();
        Parser parser = new Parser();
        File myObj = new File(Storage.FILENAME);
        Storage data = new Storage(myObj);
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(dukeBot, parser, data);
        });

        userInput.setOnAction((event) -> {
            handleUserInput(dukeBot, parser, data);
        });
        try {
            if (myObj.createNewFile()) {
                dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(
                        new Label("New File created: " + myObj.getName() + "\nWelcome!"), new ImageView(duke)));
                dukeBot.todoList = new List();
            } else {
                dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(
                        new Label("Data exists" + "\nWelcome!"), new ImageView(duke)));
                dukeBot.todoList = new List(data.load());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     *
     * @param dukeBot The DukeBot for the run.
     * @param parser The parser used for processing input.
     * @param data The data used for storage.
     */
    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html
    // with minor modifications
    private void handleUserInput(Duke dukeBot, Parser parser, Storage data) {
        if (userInput.getText().equals(QUIT_COMMAND)) {
            System.exit(0);
        }
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText(), dukeBot, parser));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        data.writeToFile(dukeBot.todoList);
        userInput.clear();
    }

    /**
     * Gets the correct response from the input given.
     *
     * @param input The input by user.
     * @param dukeBot The dukeBot used.
     * @param parser The parser used.
     * @return The correct response based on input given.
     */
    private String getResponse(String input, Duke dukeBot, Parser parser) {
        try {
            assert !input.equals("bye") : "The command should not be bye";
            return dukeBot.todoList.addTask(input, parser);
        } catch (IOException e) {
            return "ERROR!";
        }
    }
}
