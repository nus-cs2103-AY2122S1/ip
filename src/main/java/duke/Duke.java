package duke;

import java.io.File;

import duke.commands.Command;
import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke extends Application {
    private Ui ui;
    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public Duke() {
        this("data/duke.txt");
    }

    /**
     * Constructor for Duke instance.
     * @param filePath The path where the data file is to be stored
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(new File(filePath));
        tasks = new TaskList(storage.load());
        parser = new Parser();
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        ui.showWelcomeMessage();

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.getUserInput();
                Command c = parser.parseCommand(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e);
            }
        }

        ui.showGoodbyeMessage();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    @Override
    public void start(Stage stage) {
        // Setting up required components
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("SAY");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);


        scene = new Scene(mainLayout);

        // Formatting stage
        stage.setTitle("Duke Pro MAX (TM)");
        stage.setResizable(false);
        stage.setMinHeight(500.0);
        stage.setMinWidth(700.0);

        mainLayout.setPrefSize(700.0, 500.0);

        scrollPane.setPrefSize(700, 450);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setStyle("-fx-font-size: 18px");

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        dialogContainer.setSpacing(10);

        userInput.setPrefWidth(600.0);
        userInput.setMinHeight(50.0);
        userInput.setStyle("-fx-background-color: #f9a26c; -fx-font-size: 20px;"
                + "-fx-font-weight: bolder");

        sendButton.setPrefWidth(100.0);
        sendButton.setMinHeight(50.0);
        sendButton.setStyle("-fx-background-color: #f26627; -fx-text-fill: #efeeee;"
                + "-fx-font-size: 20px; -fx-font-weight: bolder");

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        this.ui = new Ui(dialogContainer);
        stage.setScene(scene);
        stage.show();
        ui.showWelcomeMessage();

        // Handle user input
        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> handleUserInput(userInput.getText()));

        userInput.setOnAction((event) -> handleUserInput(userInput.getText()));
    }


    private void handleUserInput(String input) {
        try {
            ui.showUserUiMessage(input);
            Command c = parser.parseCommand(input);
            c.execute(tasks, ui, storage);
            if (c.isExit()) {
                Platform.exit();
            }
        } catch (DukeException e) {
            ui.showErrorMessage(e);
        }
        userInput.clear();
    }
}
