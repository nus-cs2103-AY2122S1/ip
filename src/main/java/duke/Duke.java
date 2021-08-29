package duke;

import duke.command.Command;
import duke.exception.DukeException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main class of the project.
 */
public class Duke extends Application {

    private Storage storage;
    private Ui ui;
    private final TaskList tasks;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;



    /**
     * Empty constructor for javafx.
     * Ui initialization is delayed to accept dialogContainer.
     */
    public Duke() {
        String userPath = System.getProperty("user.dir")
                + "\\data\\save.txt";
        storage = new Storage(userPath);
        tasks = storage.load();
    }

    /**
     * Private constructor to initialize other components.
     *
     * @param filePath path to the log file to be read by Storage.
     */
    private Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    /**
     * Main method.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        String userPath = System.getProperty("user.dir")
                + "\\data\\save.txt";
        new Duke(userPath).run();
    }

    /**
     * Main body to loop through reading commands and displaying outputs.
     */
    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            ui.showLine();
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
            ui.showLine();
        }
    }

    /**
     * Starts the GUI display.
     *
     * @param primaryStage jfx stage to be used.
     */
    @Override
    public void start(Stage primaryStage) {
        // GUI components initialization
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();
        scene = new Scene(mainLayout);

        // Initialize ui
        ui = new Ui(dialogContainer);

        // GUI window settings
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        primaryStage.setTitle("Duk");
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(600.0);
        primaryStage.setMinWidth(400.0);
        mainLayout.setPrefSize(400.0, 600.0);

        // GUI components styling
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

        // javaFx lifecycle hooks
        sendButton.setOnMouseClicked((event) -> processInput(userInput.getText()));
        userInput.setOnAction((event) -> processInput(userInput.getText()));

        // Starts display loop
        primaryStage.setScene(scene);
        primaryStage.show();

        ui.showWelcome();
    }

    /**
     * Event handler to process user input from GUI.
     *
     * @param stringInput String supplied by user.
     */
    private void processInput(String stringInput) {
        ui.echoUser(stringInput);
        try {
            Command parsedCommand = Parser.parse(stringInput);
            parsedCommand.execute(tasks, ui, storage);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            System.out.println(e.getMessage());
        }
        userInput.clear();
    }
}
