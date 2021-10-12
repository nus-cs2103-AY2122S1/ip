package duke;

import java.time.DateTimeException;

import duke.command.Command;
import duke.exception.DukeException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main class of the project.
 */
public class Duke extends Application {
    private static final double STAGE_WIDTH = 600.0;
    private static final double STAGE_HEIGHT = 400.0;
    private static final double SCROLL_WIDTH = 385.0;
    private static final double SCOLL_HEIGHT = 535.0;
    private static final double DEFAULT_PADDING = 10.0;
    private static final double INPUT_WIDTH = 325.0;
    private static final double BUTTON_WIDTH = 55.0;

    private final Storage storage;
    private Ui ui;
    private final TaskList tasks;
    private ScrollPane scrollPane;
    private TextField userInput;

    /**
     * Class constructor for javafx.
     * Ui initialization is delayed to accept dialogContainer.
     */
    public Duke() {
        String userPath = "data/save.txt";
        storage = new Storage(userPath);
        tasks = storage.load();
    }

    /**
     * Class constructor to initialize with file path.
     *
     * @param filePath path to the log file to be read by Storage.
     */
    private Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    /**
     * Starts Duk as a command line program.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        String userPath = "data/save.txt";
        new Duke(userPath).run();
    }

    /**
     * Loops infinitely between user input and display output.
     */
    private void run() {
        assert storage != null : "Storage not properly initialized";
        assert tasks != null : "TaskList not properly initialized";
        boolean isExit = false;
        ui.showWelcome();

        while (!isExit) {
            ui.showLine();
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | DateTimeException e) {
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
        VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        Button sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();
        Scene scene = new Scene(mainLayout);

        // Initialize ui
        ui = new Ui(dialogContainer);

        // Style ui
        setLayout(mainLayout, primaryStage, dialogContainer, sendButton);

        // javaFx lifecycle hooks
        sendButton.setOnMouseClicked((event) -> processInput(userInput.getText()));
        userInput.setOnAction((event) -> processInput(userInput.getText()));

        // Set theme
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        dialogContainer.setStyle("-fx-background-color: transparent; -fx-base: transparent;");
        sendButton.setStyle("-fx-background-color: #2b2b2b; -fx-text-fill: white");
        mainLayout.setStyle("-fx-control-inner-background: #2b2b2b;");
        setBackground(mainLayout);

        // Starts display loop
        primaryStage.setScene(scene);
        primaryStage.show();

        ui.showWelcome();
    }

    /**
     * Sets the background of the ui.
     *
     * @param anchorPane Ui element to be set.
     */
    private void setBackground(AnchorPane anchorPane) {
        Image image = new Image(this.getClass().getResourceAsStream("/images/bg.jpg"));
        BackgroundSize size = new BackgroundSize(STAGE_WIDTH,
                STAGE_HEIGHT,
                true,
                true,
                false,
                true);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                null,
                null,
                null,
                size);
        anchorPane.setBackground(new Background(backgroundImage));
    }

    /**
     * Sets the ui layout.
     *
     * @param mainLayout main AnchorPane.
     * @param primaryStage stage to be displayed.
     * @param dialogContainer VBox containing all dialog elements.
     * @param sendButton Send button.
     */
    private void setLayout(AnchorPane mainLayout,
                           Stage primaryStage,
                           VBox dialogContainer,
                           Button sendButton) {

        // GUI window settings
        primaryStage.setTitle("Duk");
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(STAGE_WIDTH);
        primaryStage.setMinWidth(STAGE_HEIGHT);
        mainLayout.setPrefSize(STAGE_HEIGHT, STAGE_WIDTH);
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        // GUI components styling
        scrollPane.setPrefSize(SCROLL_WIDTH, SCOLL_HEIGHT);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        dialogContainer.setPrefHeight(-1);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        dialogContainer.setPadding(new Insets(DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING));
        dialogContainer.setSpacing(DEFAULT_PADDING);
        userInput.setPrefWidth(INPUT_WIDTH);
        sendButton.setPrefWidth(BUTTON_WIDTH);
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 10.0);
        AnchorPane.setRightAnchor(sendButton, 5.0);
        AnchorPane.setLeftAnchor(userInput , 5.0);
        AnchorPane.setBottomAnchor(userInput, 10.0);
    }

    /**
     * Processes user input from GUI.
     *
     * @param stringInput String supplied by user.
     */
    private void processInput(String stringInput) {
        ui.printUserDialog(stringInput);
        assert stringInput != null : "processInput requires non empty input string";
        try {
            Command parsedCommand = Parser.parse(stringInput);
            parsedCommand.execute(tasks, ui, storage);
        } catch (DukeException | DateTimeException e) {
            ui.showError(e.getMessage());
        }
        userInput.clear();
    }
}
