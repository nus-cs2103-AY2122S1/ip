package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Runs the program Duke.
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /** JavaFX fields */
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/Tim.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Andrew.jpg"));

    /**
     * Constructs a Duke object to start the program.
     *
     * Calls storage to load the TaskList data from the hard disk.
     *
     * @param filePath relative file path of output file.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            this.tasks = storage.load();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            this.tasks = new TaskList();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Empty constructor for JavaFX launcher
     */
    public Duke() {
    }

    /**
     * Runs the program
     *
     * Handles errors within a while loop.
     */
    public void run() {
        ui.greet();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showDivider();
                this.parser = new Parser(tasks, ui, storage);
                parser.parse(fullCommand);
                isExit = ui.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException | DateTimeParseException e) {
                System.err.println(e.getMessage());
            } finally {
                ui.showDivider();
            }
        }
    }

    @Override
    public void start(Stage stage) {
        // Step 1. Setting up required components.
        // The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene); // Setting the stage to show our screen

        // Step 2. Formatting the window to look as expected
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
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener(observable -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });


        stage.show(); // Render the stage.
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text tat has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing
     * Dukes's reply and then appends the dialog container.
     * Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                new DialogBox(userText, new ImageView(user)),
                new DialogBox(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user.
     * Replace this stub with your complete method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Initialises an instance of Duke and runs it.
     *
     * @param args
     */
    public static void main(String[] args) {
        Duke duke = new Duke("data/tasks.txt");
        duke.run();
    }
}
