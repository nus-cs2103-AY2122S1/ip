package duke;

import duke.command.Command;
import duke.exception.DukeException;            
import duke.gui.DialogBox;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Represents a program called Duke. A <code> Duke </code> is a software which
 * acts as a todolist.
 */
public class Duke extends Application {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/ronaldo.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/messi.png"));

    /**
     * Constructor for Duke. Initialises Ui, Storage and TaskList objects.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList(storage.initialise());
    }

    /**
     * Runs the duke program.
     */
    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readInput();
                Command c = Parser.parse(userInput, ui, tasks);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Processes the input string and returns a string to be displayed to
     * the user on the GUI.
     *
     * @param input String input to be processed.
     * @return String to be displayed to the user on the GUI.
     */
    public String processInput(String input) {
        try {
            Command c = Parser.parse(input, ui, tasks);
            return c.getExecutedString(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Main method for Duke program.
     *
     * @param args Arguments for main method.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    @Override
    public void start(Stage stage) {
        // part 1
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);


        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
        scene.getRoot().setStyle("-fx-font-family: 'Courier New'"); //fix

        stage.setScene(scene);
        stage.show();

        // part 2
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(400.0, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // part 3
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(330.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);


        //Part 4. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
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
        String dukeResponse = processInput(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), user),
                DialogBox.getDukeDialog(dukeResponse, duke)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
