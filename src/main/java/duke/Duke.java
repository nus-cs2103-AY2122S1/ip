package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

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

import duke.command.Command;
import duke.controls.DialogBox;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;


/**
 * The driver class for the Duke chat bot.
 */
public class Duke extends Application {
    /**
     * Object to handle UI-related functions (e.g. printing messages and lines)
     */
    private final Ui ui;
    /**
     * Object to handle loading from/save to a save file
     */
    private final Storage storage;
    /**
     * Object to represent the user's task list (e.g. add/delete/mark as done)
     */
    private TaskList taskList;

    private final Image user =
            new Image(this.getClass().getResourceAsStream("/images/TakeNRG.png"));
    private final Image duke =
            new Image(this.getClass().getResourceAsStream("/images/GivePLZ.png"));
    private TextField userInput;
    private VBox dialogContainer;

    /**
     * Creates a Duke chat bot instance, using a file path for loading/saving.
     *
     * @param filePath Relative path to the location of the save file.
     */
    public Duke(Path filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTasksFromFile());
        } catch (FileNotFoundException e) {
            ui.showFileNotFoundError();
            taskList = new TaskList();
        } catch (Exception e) {
            ui.showLoadingError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Creates a Duke chat bot instance, using a default file path for loading/saving.
     */
    public Duke() {
        this(Paths.get(System.getProperty("user.dir"), "data", "tasks.txt"));
    }

    /**
     * Runs the actual chat bot program.
     */
    public void run() {
        ui.showIntroduction();
        boolean isTerminated = false;
        while (!isTerminated) {
            try {
                String input = ui.readInput();
                ui.showLine();
                Command command = Parser.parseCommandFromInput(input);
                taskList = command.execute(taskList, ui, storage);
                isTerminated = command.isTerminated();
            } catch (IOException e) {
                ui.showError("The data failed to save to the save file with error:"
                        + e.getMessage());
            } catch (IllegalArgumentException e) {
                // When invalid command is given, it is unable to be parsed into the enum
                ui.showError("You have entered an invalid command.");
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    /* TODO: Delete this once JavaFX is fully implemented
    /**
     * Starts the whole program.
     * @param args Some input arguments. (Unused)

    public static void main(String[] args) {
        Path filePath = Paths.get(System.getProperty("user.dir"), "data", "tasks.txt");
        new Duke(filePath).run();
    }*/

    @Override
    public void start(Stage stage) {
        ScrollPane scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

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

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());
    }


    /**
     * Creates a DialogBox containing the user's input.
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

    private String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
