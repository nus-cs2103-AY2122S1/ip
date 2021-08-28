package bob;

import bob.exception.DirectoryNotFoundException;
import bob.exception.FileNotFoundException;

import java.io.File;
import java.util.Objects;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.util.Duration;

/**
 * A chatbot that stores, displays and alters the user's task list based on the user input.
 */
public class Bob extends Application{
    /** Storage object that deals with loading tasks from the file and saving tasks in the file */
    private Storage storage;

    /** List of user tasks */
    private TaskList tasks;

    /** Ui object that deals with interactions with the user */
    private Ui ui;

    /** Parser object that deals with making sense of the user commands */
    private Parser parser;

    /** Whether the data directory is already present in the user computer */
    boolean isDirectoryPresent = true;

    /** Whether the bob.txt file is already present within the data directory in the user computer */
    boolean isBobFilePresent = true;

    /** UI element that allows users to scroll up and down the stage to view more content */
    private ScrollPane scrollPane;

    /** UI element that lays out its children in a single vertical column */
    private VBox dialogContainer;

    /** UI element that allows users to type in their input */
    private TextField userInput;

    /** UI element that allows Bob to take in the user input when the user clicks on it */
    private Button sendButton;

    /** UI element that contains all the nodes to be shown in the GUI */
    private Scene scene;

    /** Image representing the user in the chat */
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));

    /** Image representing Bob in the chat */
    private Image bob = new Image(this.getClass().getResourceAsStream("/images/DaBob.jpeg"));

    /**
     * Constructor for a new Bob instance.
     */
    public Bob() {
        ui = new Ui();
        storage = new Storage(new File("").getAbsolutePath() + "/data");
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (DirectoryNotFoundException e) {
            isDirectoryPresent = false;
            tasks = new TaskList();
            storage.makeDataDirectory();
            storage.makeBobFile();
        } catch (FileNotFoundException e) {
            isBobFilePresent = false;
            tasks = new TaskList();
            storage.makeBobFile();
        }
    }

    /**
     * Does not do anything.
     *
     * @param args String array that acts as the argument to the main method.
     */
    public static void main(String[] args) {
    }

    /**
     * Sets up the GUI for Bob.
     *
     * @param stage The primary stage provided by JavaFX for the GUI.
     */
    @Override
    public void start(Stage stage) {
        // Step 1. Setting up required components.
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

        // Step 2. Formatting the window to look as expected.
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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Displaying the initial message when Bob first starts up.
        dialogContainer.getChildren().addAll(
                DialogBox.getBobDialog(new Label(ui.getStartMessage()), new ImageView(bob))
        );

        // Displaying the appropriate Bob messages if the data directory or bob.txt file do not exist yet.
        if (!isDirectoryPresent) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getBobDialog(new Label(ui.getDirectoryLoadingErrorMessage()), new ImageView(bob))
            );
        } else if (!isBobFilePresent) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getBobDialog(new Label(ui.getFileLoadingErrorMessage()), new ImageView(bob))
            );
        }

        // Displaying Bob's greeting message once initialisation is completed.
        dialogContainer.getChildren().addAll(
                DialogBox.getBobDialog(new Label(ui.getGreetingMessage()), new ImageView(bob))
        );

        // Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Takes in the user input and displays both the user input and Bob's response in the GUI.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        if (Objects.equals(userInput.getText(), "bye")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, new ImageView(user)),
                    DialogBox.getBobDialog(new Label(ui.getGoodbyeMessage()), new ImageView(bob))
            );
            userInput.clear();

            // Leave a short pause of 2 seconds after the user inputs "bye" to display Bob's goodbye message
            // before automatically closing the window and terminating the program.
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished( event -> Platform.exit() );
            delay.play();
        } else {
            Label dukeText = new Label(parser.getResponse(userInput.getText(), ui, tasks, storage));
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, new ImageView(user)),
                    DialogBox.getBobDialog(dukeText, new ImageView(bob))
            );
            userInput.clear();
        }
    }
}
