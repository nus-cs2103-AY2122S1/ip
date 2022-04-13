package duke;

import duke.commands.Command;
import duke.commands.UndoCommand;
import duke.parser.Parser;
import javafx.application.Application;
import javafx.geometry.Insets;
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

import duke.storage.Storage;
import duke.ui.TextUi;


/**
 * Represents a personalised chat bot for CS2103/T iP.
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/dog0.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/dog1.png"));
    private TaskList prevTasks;

    /**
     * An empty constructor for a Duke chat bot.
     */
    public Duke() {}

    /**
     * A constructor for a Duke chat bot.
     *
     * @param filePath The path of the file the task list is saved in.
     */
    public Duke(String filePath) {
        this.tasks = new TaskList();
        this.storage = new Storage(filePath, tasks);
        try {
            this.storage.readFile();
        } catch (DukeException e) {
            TextUi.showErrorMessage(e.getMessage());
        }
    }

    /**
     * Starts the programme with GUI.
     * @param stage Stage provided by JavaFX.
     */
    @Override
    public void start(Stage stage) {
        tasks = new TaskList();
        prevTasks = new TaskList();
        storage = new Storage("data/duke.txt", tasks);
        try {
            storage.readFile();
        } catch (DukeException e) {
            TextUi.showErrorMessage(e.getMessage());
        }
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
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

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setSpacing(30.0);
        dialogContainer.setPadding(new Insets(30));
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        dialogContainer.getChildren().add(DialogBox.getDukeDialog(new Label("Welcome to Duke!"), new ImageView(duke)));

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
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
    private String getResponse(String text) {
        String response = "";
        try {
            Command cmd = Parser.parseCommand(text);
            if (UndoCommand.class.isInstance(cmd)) {
                ((UndoCommand) cmd).setStorage(this.storage);
                response += cmd.execute(this.prevTasks);
                this.tasks = this.prevTasks.duplicate();
            } else {
                this.prevTasks = this.tasks.duplicate();
                response += cmd.execute(this.tasks);
            }
            this.storage.copyToFile();
        } catch (DukeException e) {
            return TextUi.showErrorMessage(e.getMessage());
        }
        assert response != "" : "response should not be empty";
        return response;
    }
}