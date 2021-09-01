package duke;

import java.util.Scanner;

// TODO: check
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    // TODO: check
    public Duke() {

    }

    /**
     * Constructs an instance of the Duke program.
     * If there is no file at the specified filePath, a new file will be created;
     * else, the tasks in the file will be read and saved.
     *
     * @param filePath The file path of the file containing the list of tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printResponse(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs Duke's main program.
     * Main logic of the program, which will continue to accept user input until user enters 'bye'.
     */
    public void run() {
        ui.printResponse("Hello! I'm Duke", "What can I do for you?");
        Parser parser = new Parser();
        Scanner scan = new Scanner(System.in);
        boolean active = true;
        while (active) {
            String command = scan.nextLine();
            try {
                active = parser.parse(command, tasks, ui);
            } catch (DukeException e) {
                ui.printResponse(e.getMessage());
            }
            try {
                storage.rewriteData(tasks.convertToSaveFormat());
            } catch (DukeException e) {
                ui.printResponse(e.getMessage());
            }
        }
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

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    // TODO: check
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

    // TODO: check
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Creates a new instance of Duke.
     * Instantiates the Duke instance with a file path and runs it.
     *
     * @param args String arguments if needed.
     */
    public static void main(String[] args) {
        new Duke("dukedata.txt").run();
    }
}
