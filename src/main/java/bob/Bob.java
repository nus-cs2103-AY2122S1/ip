package bob;

import bob.exception.DirectoryNotFoundException;
import bob.exception.FileNotFoundException;

import java.io.File;

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
 * Represents a chatbot that stores, displays and alters the user's task list based on the user input.
 */
public class Bob extends Application{
    /** Storage object that deals with loading tasks from the file and saving tasks in the file */
    private Storage storage;

    /** List of user tasks */
    private TaskList tasks;

    /** Ui object that deals with interactions with the user */
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image bob = new Image(this.getClass().getResourceAsStream("/images/DaBob.jpeg"));

    /**
     * Constructor for creating a new Bob instance.
     *
     * @param filePath Pathname to bob.txt file that will be used to store the list of tasks.
     */
    public Bob(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        ui.showStart();
        try {
            tasks = new TaskList(storage.load());
        } catch (DirectoryNotFoundException e) {
            ui.showDirectoryLoadingError();
            tasks = new TaskList();
            storage.makeDataDirectory();
            storage.makeBobFile();
        } catch (FileNotFoundException e) {
            ui.showFileLoadingError();
            tasks = new TaskList();
            storage.makeBobFile();
        }
    }

    /**
     * Runs the Bob instance to begin the chat.
     */
    public void run() {
        ui.showGreeting();
        Parser parser = new Parser();
        parser.run(ui, tasks, storage);
        ui.showGoodbye();
    }

    /**
     * Main method that finds the pathname to the storage bob.txt file and creates and runs a new Bob instance.
     *
     * @param args String array that acts as the argument to the main method.
     */
    public static void main(String[] args) {
        String currDirectory = new File("").getAbsolutePath();
        String dataDirectory = currDirectory + "/data";
        new Bob(dataDirectory).run();
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

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Bob");
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

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    public Bob() {};

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Bob heard: " + input;
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getBobDialog(dukeText, new ImageView(bob))
        );
        userInput.clear();
    }
}
