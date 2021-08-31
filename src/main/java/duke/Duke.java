package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.command.Storage;
import duke.command.Ui;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidTaskException;
import duke.exception.MissingTaskException;
import duke.exception.MissingTimeException;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class runs a personal assistant chatbot that helps a person keep track of various tasks.
 * Commands for the bot are: list, mark, find, delete, bye, event, deadline, and todo.
 * Unrecognised commands will be met with a prompt to enter a recognised one instead.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/penguin_2.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/penguin_cute.jpg"));

    /**
     * Initiates the chatbot by loading the given file.
     *
     * @param filepath The location of the file to load.
     */
    public Duke(String filepath) {
        ui = new Ui("Pingu");
        storage = new Storage(filepath);

        try {
            tasks = new TaskList(storage.loadListData());
        } catch (FileNotFoundException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Constructor to run JFX.
     */
    public Duke() {}

    /**
     * Starts the chatbot's interactions with the user.
     */
    public void run() {
        ui.printWelcomeMessage();

        while (true) {
            try {
                String input = ui.getInput();
                String command = ui.receiveUserCommand(input);

                if (command.equals("bye")) {
                    break;
                }

                ui.printSeparator();
                tasks.performCommand(command, input);
                ui.printSeparator();

                storage.saveTasksToFile(tasks.getTasks());

            } catch (InvalidCommandException e) {
                ui.printException("InvalidCommand");
            } catch (IOException e) {
                ui.printException("IOException");
            } catch (InvalidTaskException e) {
                ui.printException("InvalidTask");
            } catch (MissingTaskException e) {
                ui.printException("MissingTask");
            } catch (MissingTimeException e) {
                ui.printException("MissingTime");
            } catch (DateTimeParseException e) {
                ui.printException("DateTimeParse");
            }
        }

        ui.printBye();
    }


    /**
     * Initiates the GUI for Duke.
     *
     * @param stage The primary stage that JavaFX provides.
     */
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

        // You will need to import `javafx.scene.layout.Region` for this.
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

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
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
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Initializes the chatbot.
     */
    public static void main(String[] args) {
        new Duke("data/duke_list_data.txt").run();
    }
}
