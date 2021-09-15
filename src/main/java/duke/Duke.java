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
    private AnchorPane mainLayout;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/duke.jpg"));

    /** Encapsulates the storage of tasks in a file within the user's computer for continuity across Duke sessions. */
    private final Storage STORAGE;
    /**
     * Stores the tasks when the Duke program runs.
     * Allows one to perform actions on those tasks like add and delete.
     */
    private TaskList tasks;
    /** Handles the UI aspect of the Duke program, relaying messages to the user. */
    private final Ui UI;

    private final Parser PARSER;

    // TODO: check
    /**
     * Constructs an instance of the Duke program.
     * If there is no file at the filePath, a new file will be created;
     * else, the tasks in the file will be read and saved.
     */
    public Duke() {
        UI = new Ui();
        final String FILEPATH = "dukedata.txt";
        STORAGE = new Storage(FILEPATH);
        try {
            tasks = new TaskList(STORAGE.load());
        } catch (DukeException e) {
            // TODO: smth with this
            UI.getResponse(e.getMessage());
            tasks = new TaskList();
        }

        UI.getResponse("Hello! I'm Duke", "What can I do for you?");
        PARSER = new Parser();
    }

    // TODO: check
    @Override
    public void start(Stage stage) {
        // insert assert here
        setUpComponents(stage);
        formatWindow(stage);
    }

    private void setUpComponents(Stage stage) {
        // insert assert here?
        //Step 1. Setting up required components
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();

        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");
        mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        // remove after
        /*
        scrollPane.setBackground(new Background(new BackgroundFill(Color.RED,
                CornerRadii.EMPTY,
                Insets.EMPTY)));
        dialogContainer.setBackground(new Background(new BackgroundFill(Color.RED,
                CornerRadii.EMPTY,
                Insets.EMPTY)));
        dialogContainer.setStyle("-fx-background-color: #FF0000;");
        mainLayout.setBackground(new Background(new BackgroundFill(Color.RED,
                CornerRadii.EMPTY,
                Insets.EMPTY)));
         */

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
    }

    private void formatWindow(Stage stage) {
        // insert assert here?
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

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
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
            currentStatus = PARSER.parse(input, tasks, UI);
        } catch (DukeException e) {
            UI.getResponse(e.getMessage());
        }
        try {
            STORAGE.rewriteData(tasks.convertToSaveFormat());
        } catch (DukeException e) {
            // TODO
            UI.getResponse(e.getMessage());
        }

        if (currentStatus == DukeStatus.INACTIVE) {
            Platform.exit();
            System.exit(0);
        }
        return currentStatus.getResponse();
    }
}
