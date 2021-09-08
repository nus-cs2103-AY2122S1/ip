package duke;

// TODO: check
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Duke extends Application {
    // TODO: check
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/duke.jpg"));

    /** Encapsulates the storage of tasks in a file within the user's computer for continuity across Duke sessions. */
    private Storage storage;
    /**
     * Stores the tasks when the Duke program runs.
     * Allows one to perform actions on those tasks like add and delete.
     */
    private TaskList tasks;
    /** Handles the UI aspect of the Duke program, relaying messages to the user. */
    private Ui ui;

    /** The file path of the file containing the list of tasks. */
    private String filePath = "dukedata.txt";

    private Parser parser;

    // TODO: check
    /**
     * Constructs an instance of the Duke program.
     * If there is no file at the filePath, a new file will be created;
     * else, the tasks in the file will be read and saved.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            // TODO: smth with this
            ui.getResponse(e.getMessage());
            tasks = new TaskList();
        }

        ui.getResponse("Hello! I'm Duke", "What can I do for you?");
        parser = new Parser();
    }

    // TODO: check
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


        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }


    // TODO: check
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        DukeStatus currentStatus = DukeStatus.ACTIVE;
        try {
            currentStatus = parser.parse(input, tasks, ui);
        } catch (DukeException e) {
            ui.getResponse(e.getMessage());
        }
        try {
            storage.rewriteData(tasks.convertToSaveFormat());
        } catch (DukeException e) {
            // TODO
            ui.getResponse(e.getMessage());
        }

        if (currentStatus == DukeStatus.INACTIVE) {
            Platform.exit();
            System.exit(0);
        }
        return currentStatus.getResponse();
    }
}
