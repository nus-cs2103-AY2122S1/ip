package duke;
import duke.exceptions.DukeException;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 *  Duke class that contains run and main method to run the program
 *
 * @author Chua Sue-Ann
 */
public class Duke extends Application {

    private duke.Storage storage;
    private duke.TaskList taskList;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Public constructor for Duke class. Creates instance of Duke class.
     *
     * @param file File that data will be stored in later.
     * @return Instance of Duke class.
     */
    public Duke(File file) {
        this.storage = new duke.Storage(file);
        try {
            this.taskList = new duke.TaskList();
        } catch (Exception e) {

        }
    }

    public Duke() {}

    /**
     * Run method triggers file to be created and welcomes user with welcome message.
     *
     * @throws DukeException
     */
    public void run() throws DukeException {
        try {
            File dukeFile = new File("data/duke.txt");
            PrintWriter writer  = this.storage.load();

            Ui.showWelcomeMessage();

            Parser.parseCommand(taskList, dukeFile, writer);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void start(Stage stage) {
        // Step1 setting up required components
        // Container for the content of the chat to scroll
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
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Main method for program. Creates file path and creates file.
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Files.createDirectories(Paths.get("data/"));
        File dukeFile = new File("data/duke.txt");
        new Duke(dukeFile).run();
    }
}

