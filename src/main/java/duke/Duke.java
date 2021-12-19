package duke;

import duke.command.ICommand;
import duke.io.Parser;
import duke.io.ResponseManager;
import duke.io.UserDialogBox;
import duke.task.TaskManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The main class for Duke.
 */
public class Duke extends Application {

    // GUI components
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Button helpButton;
    private Scene scene;

    // Backend components
    private final ResponseManager responseManager;
    private final TaskManager taskManager;
    private final Storage storage;
    private final Parser parser = new Parser();

    // Image resources
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/Jesse.jpg"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    /**
     * Constructor for Duke.
     *
     * @param saveFileLocation The path to where the save file is to be stored.
     */
    public Duke(String saveFileLocation) {
        responseManager = new ResponseManager();
        storage = new Storage(saveFileLocation);
        taskManager = new TaskManager(storage.loadSave());
    }

    /**
     * Starts running the program.
     *
     * @param stage The UI stage to display the chat.
     */
    public void start(Stage stage) {
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");
        helpButton = new Button("Help");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton, helpButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("The Duke");
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

        helpButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> handleUserInput());

        userInput.setOnAction((event) -> handleUserInput());

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        System.out.println(react(userText));
        String dukeText = react(userText);

        dialogContainer.getChildren().addAll(
                UserDialogBox.getUserDialog(userText, userImage),
                UserDialogBox.getDukeDialog(dukeText, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Generates a response based on the given input.
     */
    public String getResponse(String input) {
        ICommand c = parser.getInput(input);
        c.execute(taskManager, responseManager, storage);
        return c.getReply();
    }

    /**
     * Runs the program until the bye command is used to end it.
     */
    public String react(String input) {
        ICommand c = parser.getInput(input);
        assert(c != null);
        c.execute(taskManager, responseManager, storage);
        return c.getReply();
    }
}
