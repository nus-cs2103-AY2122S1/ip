package bruh.ui;

import bruh.Bruh.CommandRunner;
import bruh.command.Command;
import bruh.exception.BruhException;
import bruh.parser.Parser;
import javafx.animation.PauseTransition;
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
 * Encapsulates the user interface function of Bruh.
 */
public class Ui {
    private static final String SEND_BUTTON_TEXT = "Send";
    private static final String STAGE_TITLE = "Bruh";
    private static final String INVALID_TASK_NUM_ERROR_MSG =
            "Please specify a valid task number (use 'list' to view your tasks).";

    private static final String LOGO = "  /$$                           /$$      \n"
            + " | $$                          | $$      \n"
            + " | $$$$$$$   /$$$$$$  /$$   /$$| $$$$$$$ \n"
            + " | $$__  $$ /$$__  $$| $$  | $$| $$__  $$\n"
            + " | $$  \\ $$| $$  \\__/| $$  | $$| $$  \\ $$\n"
            + " | $$  | $$| $$      | $$  | $$| $$  | $$\n"
            + " | $$$$$$$/| $$      |  $$$$$$/| $$  | $$\n"
            + " |_______/ |__/       \\______/ |__/  |__/\n";
    private static final String GREETING = "Hi! How can\n\n" + LOGO + "\nhelp you today?\n";
    private static final String GREETING_LABEL_STYLE = "-fx-font-family: 'monospaced';";

    private Image bruhImage;
    private Image userImage;
    private Image errorImage;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInputField;
    private Button sendButton;
    private AnchorPane mainLayout;
    private Scene scene;

    /**
     * Initializes the user interface.
     *
     * @param stage         The top-level stage containing all UI elements.
     * @param commandRunner The handler function for the user input.
     */
    public void init(Stage stage, CommandRunner commandRunner) {
        setupUiElements(stage);
        setupEventHandlers(commandRunner);

        stage.setScene(scene);
        stage.show();

        displayGreeting();
    }

    /**
     * Displays the greeting dialog to the user.
     */
    private void displayGreeting() {
        Label greetingText = new Label(GREETING);
        greetingText.setStyle(GREETING_LABEL_STYLE);
        ReceiverDialogBox greetingMessage =
                new ReceiverDialogBox(greetingText, new ImageView(bruhImage));
        dialogContainer.getChildren().add(greetingMessage);
    }

    /**
     * Set up the UI elements used by Bruh.
     *
     * @param stage The stage on which the UI elements are set up.
     */
    private void setupUiElements(Stage stage) {
        initializeElements();

        configStage(stage);
        configMainLayout();
        configScrollPane();
        configDialogContainer();
        configUserInputField();
        configSendButton();
        configAnchorPane();

        setupNodeTree();
    }

    //@@author LMAOboxhack-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html
    // with some refactoring & modifications

    /**
     * Initializes all the UI elements.
     */
    private void initializeElements() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        userInputField = new TextField();
        sendButton = new Button(SEND_BUTTON_TEXT);
        mainLayout = new AnchorPane();
        scene = new Scene(mainLayout);

        assert getClass()
                .getResourceAsStream("/images/bruh.jpg") != null : "Bruh image cannot be null";
        assert getClass()
                .getResourceAsStream("/images/user.png") != null : "User image cannot be null";
        assert getClass()
                .getResourceAsStream("/images/error.png") != null : "Error image cannot be null";

        bruhImage = new Image(this.getClass().getResourceAsStream("/images/bruh.jpg"));
        userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
        errorImage = new Image(this.getClass().getResourceAsStream("/images/error.png"));
    }

    /**
     * Sets up the node tree.
     */
    private void setupNodeTree() {
        scrollPane.setContent(dialogContainer);
        mainLayout.getChildren().addAll(scrollPane, userInputField, sendButton);
    }

    /**
     * Configures the anchor pane UI element.
     */
    private void configAnchorPane() {
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInputField, 1.0);
        AnchorPane.setBottomAnchor(userInputField, 1.0);
    }

    /**
     * Configures the send button UI element.
     */
    private void configSendButton() {
        sendButton.setPrefWidth(55.0);
    }

    /**
     * Configures the user input UI element.
     */
    private void configUserInputField() {
        userInputField.setPrefWidth(435.0);
    }

    /**
     * Configures the dialog container UI element.
     */
    private void configDialogContainer() {
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.setSpacing(25.0);
    }

    /**
     * Configures the scroll pane UI element.
     */
    private void configScrollPane() {
        scrollPane.setPrefSize(485.0, 535.0);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
    }

    /**
     * Configures the main layout UI element.
     */
    private void configMainLayout() {
        mainLayout.setPrefSize(500.0, 600.0);
    }

    /**
     * Configures the stage UI element.
     */
    private void configStage(Stage stage) {
        stage.setTitle(STAGE_TITLE);
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(500.0);
    }

    //@@author

    /**
     * Set up the event handlers for the UI elements.
     *
     * @param commandRunner The command runner to be attached to events.
     */
    private void setupEventHandlers(CommandRunner commandRunner) {
        sendButton.setOnMouseClicked(event -> handleUserInput(commandRunner));
        userInputField.setOnAction(event -> handleUserInput(commandRunner));
        dialogContainer.heightProperty().addListener(observable -> scrollPane.setVvalue(1.0));
    }

    /**
     * Handles user input by parsing it into commands & redirecting said commands into the specified
     * command runner.
     *
     * @param commandRunner The command runner to be provided with commands.
     */
    private void handleUserInput(CommandRunner commandRunner) {
        String userInput = userInputField.getText();
        displayMessage(userInput, MessageType.USER);

        try {
            Command command = Parser.parseInputToCommand(userInput);
            commandRunner.runCommand(command);
            assert command.getDescription() != null : "Description cannot be null";
            displayMessage(command.getDescription(), MessageType.BOT);
            checkExit(command);
        } catch (BruhException e) {
            displayMessage(e.getMessage(), MessageType.ERROR);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            displayMessage(INVALID_TASK_NUM_ERROR_MSG, MessageType.ERROR);
        } finally {
            userInputField.clear();
        }
    }

    /**
     * Displays a message on screen to the user.
     *
     * @param message     The contents of the message to be displayed.
     * @param messageType The type of the message to be displayed.
     */
    private void displayMessage(String message, MessageType messageType) {
        Label messageText = new Label(message);

        switch (messageType) {
        case USER:
            UserDialogBox userMessage = new UserDialogBox(messageText, new ImageView(userImage));
            dialogContainer.getChildren().add(userMessage);
            break;
        case BOT:
            ReceiverDialogBox bruhMessage =
                    new ReceiverDialogBox(messageText, new ImageView(bruhImage));
            dialogContainer.getChildren().add(bruhMessage);
            break;
        // Fallthrough
        case ERROR:
        default:
            ReceiverDialogBox errorMessage =
                    new ReceiverDialogBox(messageText, new ImageView(errorImage));
            dialogContainer.getChildren().add(errorMessage);
            break;
        }
    }

    /**
     * Checks if the specified command contains an exit instruction. If it does, exits the program
     * after a short delay.
     *
     * @param command The command to be checked.
     */
    private void checkExit(Command command) {
        if (command.isExit()) {
            PauseTransition exit = new PauseTransition(Duration.seconds(2.0));
            exit.setOnFinished(event -> Platform.exit());
            exit.play();
        }
    }

    /**
     * The type of a message, corresponding with different senders.
     */
    private enum MessageType {
        USER, BOT, ERROR
    }
}
