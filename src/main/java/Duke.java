import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

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
 * Duke is a personal assistant chatbot that helps keep track of various tasks.
 *
 * @author: James Kua
 * @version: Duke-Level-8
 */
public class Duke extends Application {

    /** Stores location of the stored tasks. */
    private static final String LOCATION = "./data/duke.txt";
    /** Stores the tasks in a TaskList object. */
    private TaskList tasks;
    /** Keeps track of the tasks stored. */
    private Storage storage;
    /** Parses the user inputs. */
    private Parser parser;
    /** Deals with making sense of the user commands. */
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Constructor for Duke.
     *
     * @param filePath File path to load, write and store data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadData());
    }

    public Duke() {
        ui = new Ui();
        storage = new Storage(LOCATION);
        tasks = new TaskList(storage.loadData());
    }

    /**
     * Starts the Duke program.
     */
    public void run() {
        boolean isRunning = true;
        ui.intro();
        ui.greet();
        parser = new Parser(tasks, storage);
        while (isRunning) {
            isRunning = parser.parseCommand();
        }
    }

    @Override
    public void start(Stage stage) {

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

        //Step 1. Formatting the window to look as expected.

        //...

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

        // more code to be added here later
    }

    public static void main(String[] args) {
        new Duke(LOCATION).run();
    }
}
