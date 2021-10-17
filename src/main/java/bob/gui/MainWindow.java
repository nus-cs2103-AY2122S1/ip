package bob.gui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import bob.Storage;
import bob.TaskList;
import bob.parser.Parser;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
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


public class MainWindow extends AnchorPane {

    /** Storage object that deals with loading tasks from the file and saving tasks in the file */
    private Storage storage;

    /** List of user tasks */
    private TaskList tasks;

    /** Ui object that deals with interactions with the user */
    private Ui ui;

    /** Parser object that deals with making sense of the user commands */
    private Parser parser;

    /** UI element that allows users to scroll up and down the stage to view more content */
    private ScrollPane scrollPane;

    /** UI element that lays out its children in a single vertical column */
    private VBox dialogContainer;

    /** UI element that allows users to type in their input */
    private TextField userInput;

    /** UI element that allows Bob to take in the user input when the user clicks on it */
    private Button sendButton;

    /** Image representing the user in the chat */
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));

    /** Image representing Bob in the chat */
    private Image bob = new Image(this.getClass().getResourceAsStream("/images/DaBob.jpeg"));

    /**
     * Creates a new MainWindow instance.
     * @param storage Storage object that deals with loading tasks from the file and saving tasks in the file.
     * @param tasks List of user tasks.
     * @param ui Ui object that deals with interactions with the user.
     * @param parser Parser object that deals with making sense of the user commands.
     */
    public MainWindow(Storage storage, TaskList tasks, Ui ui, Parser parser) {
        this.storage = storage;
        this.tasks = tasks;
        this.ui = ui;
        this.parser = parser;

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        userInput.setPromptText("Talk to me here!");
        userInput.setFocusTraversable(false);

        sendButton = new Button("Send");

        this.getChildren().addAll(scrollPane, userInput, sendButton);
    }

    /**
     * Sets the darker coloured background of the MainWindow.
     */
    public void setDarkerBackground() {
        Image bg = new Image(this.getClass().getResourceAsStream("/images/Background.jpeg"));
        BackgroundImage bgImage = new BackgroundImage(bg,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(bgImage);
        this.setBackground(background);
    }

    /**
     * Formats the MainWindow to look as expected.
     *
     * @param stage The primary stage provided by JavaFX for the GUI.
     */
    public void formatWindow(Stage stage) {
        stage.setTitle("Bob");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        this.setPrefSize(400.0, 600.0);

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
     * Sets the lighter chat background of the MainWindow.
     */
    public void setLighterBackground() {
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
     * Displays Bob's greeting message in the GUI when the user first starts up Bob.
     */
    public void displayGreeting() {
        dialogContainer.getChildren().addAll(
                DialogBox.getBobDialog(new Label(ui.getStartMessage()), new ImageView(bob))
        );
    }

    /**
     * Displays the appropriate Bob messages in the GUI if the data directory or bob.txt file are not yet present.
     *
     * @param isDirectoryPresent True if the data directory is present in the user computer.
     * @param isBobFilePresent True if bob.txt exists in the data directory in the user computer.
     */
    public void handleMissingDirectoryOrFile(boolean isDirectoryPresent, boolean isBobFilePresent) {
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
    public void displayReadyMessage() {
        dialogContainer.getChildren().addAll(
                DialogBox.getBobDialog(new Label(ui.getGreetingMessage()), new ImageView(bob))
        );
    }

    /**
     * Handles user input from the GUI.
     */
    public void handleInput() {
        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());
    }

    /**
     * Takes in the user input and displays both the user input and Bob's response in the GUI.
     */
    public void handleUserInput() {
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
    public void handleBye(Label userText) {
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
    public void handleNonBye(Label userText) {
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
    public void handleHelp(Label userText, String response) {
        Hyperlink dukeText = new Hyperlink(response);
        dukeText.setVisited(true); // Set the hyperlink as visited so that the text is black.
        dukeText.setOnAction(e -> new Thread(() -> {
            try {
                Desktop.getDesktop().browse(new URI("https://feliciaivane.github.io/ip/"));
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        }).start());
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
    public void handleOthers(Label userText, String response) {
        Label dukeText = new Label(response);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getBobDialog(dukeText, new ImageView(bob))
        );
    }

    /**
     * Scrolls down to the bottom of the MainWindow every time dialogContainer's height changes.
     */
    public void scrollDown() {
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }
}
