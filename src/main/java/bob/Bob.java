package bob;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import bob.exception.DirectoryNotFoundException;
import bob.exception.FileNotFoundException;
import bob.gui.DialogBox;
import bob.gui.HelpDialogBox;
import bob.gui.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * A chatbot that stores, displays and alters the user's task list based on the user input.
 */
public class Bob extends Application {
    /** Storage object that deals with loading tasks from the file and saving tasks in the file */
    private Storage storage;

    /** List of user tasks */
    private TaskList tasks;

    /** Ui object that deals with interactions with the user */
    private Ui ui;

    /** Parser object that deals with making sense of the user commands */
    private Parser parser;

    /** Whether the data directory is already present in the user computer */
    private boolean isDirectoryPresent = true;

    /** Whether the bob.txt file is already present within the data directory in the user computer */
    private boolean isBobFilePresent = true;

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
        AnchorPane mainLayout = getMainLayout();
        setDarkerBackground(mainLayout);

        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        formatWindow(stage, mainLayout);
        setLighterBackground();
        displayGreeting();
        handleMissingDirectoryOrFile();
        displayReadyMessage();

        handleInput();

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Sets up the required components for the JavaFX GUI.
     *
     * @return Main layout of the application window.
     */
    private AnchorPane getMainLayout() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        userInput.setPromptText("Talk to me here!");
        userInput.setFocusTraversable(false);

        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        return mainLayout;
    }

    /**
     * Sets the main darker background of the GUI.
     *
     * @param mainLayout Main layout of the application window.
     */
    private void setDarkerBackground(AnchorPane mainLayout) {
        Image bg = new Image(this.getClass().getResourceAsStream("/images/Background.jpeg"));
        BackgroundImage bgImage = new BackgroundImage(bg,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(bgImage);
        mainLayout.setBackground(background);
    }

    /**
     * Formats the application window and its internal nodes to the correct size and position.
     *
     * @param stage The primary stage provided by JavaFX for the GUI.
     * @param mainLayout Main layout of the application window.
     */
    private void formatWindow(Stage stage, AnchorPane mainLayout) {
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
    }

    /**
     * Sets the paler chat background in the GUI.
     */
    private void setLighterBackground() {
        Image img = new Image(this.getClass().getResourceAsStream("/images/PaleBackground.png"));
        BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        dialogContainer.setBackground(bGround);
    }

    /**
     * Displays Bob's initial greeting in the GUI.
     */
    private void displayGreeting() {
        dialogContainer.getChildren().addAll(
                DialogBox.getBobDialog(new Label(ui.getStartMessage()), new ImageView(bob))
        );
    }

    /**
     * Displays the appropriate Bob messages in the GUI if time is needed to initialise a new data directory
     * or bob.txt file.
     */
    private void handleMissingDirectoryOrFile() {
        if (!isDirectoryPresent) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getBobDialog(new Label(ui.getDirectoryLoadingErrorMessage()), new ImageView(bob))
            );
        } else if (!isBobFilePresent) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getBobDialog(new Label(ui.getFileLoadingErrorMessage()), new ImageView(bob))
            );
        }
    }

    /**
     * Displays Bob's ready message in the GUI when the data directory and bob.txt file are both present.
     */
    private void displayReadyMessage() {
        dialogContainer.getChildren().addAll(
                DialogBox.getBobDialog(new Label(ui.getGreetingMessage()), new ImageView(bob))
        );
    }

    /**
     * Handles user input from the GUI.
     */
    private void handleInput() {
        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());
    }

    /**
     * Takes in the user input and displays both the user input and Bob's response in the GUI.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        if (Objects.equals(userInput.getText(), "bye")) {
            handleBye(userText);
        } else { // If the user types in any other command the GUI should show the corresponding response from Bob.
            handleNonBye(userText);
        }
    }

    /**
     * Displays the appropriate Bob farewell message and terminates the GUI when the user inputs "bye".
     *
     * @param userText Label containing the user input text.
     */
    private void handleBye(Label userText) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getBobDialog(new Label(ui.getGoodbyeMessage()), new ImageView(bob))
        );
        userInput.clear();

        // Leave a short pause of 2 seconds after the user inputs "bye" to display Bob's goodbye message
        // before automatically closing the window and terminating the program.
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }

    /**
     * Takes in the user input (except "bye") and displays both the user input and Bob's response in the GUI.
     *
     * @param userText Label containing the user input text.
     */
    private void handleNonBye(Label userText) {
        String response = parser.getResponse(userInput.getText(), ui, tasks, storage);
        // If the user types in the help command Bob should return a hyperlink to the help page.
        if (Objects.equals(userInput.getText(), "help")) {
            handleHelp(userText, response);
        } else { // If the user types in any other command Bob should return its response as regular text.
            handleOthers(userText, response);
        }
        userInput.clear();
    }

    /**
     * Displays a clickable link to Bob's User Guide and the rest of Bob's response in the GUI when the user
     * inputs "help".
     *
     * @param userText Label containing the user input text.
     * @param response Bob's response to the user input.
     */
    private void handleHelp(Label userText, String response) {
        Hyperlink dukeText = new Hyperlink(response);
        dukeText.setVisited(true); // Set the hyperlink as visited so that the text is black.
        dukeText.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://feliciaivane.github.io/ip/"));
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        });
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                new HelpDialogBox(dukeText, new ImageView(bob))
        );
    }

    /**
     * Takes in the user input (except "help" or "bye") and displays both the user input and Bob's response in the GUI.
     *
     * @param userText Label containing the user input text.
     * @param response Bob's response to the user input.
     */
    private void handleOthers(Label userText, String response) {
        Label dukeText = new Label(response);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getBobDialog(dukeText, new ImageView(bob))
        );
    }
}
