package bruh.ui;

import bruh.Bruh;
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
    private static final String LOGO = "  /$$                           /$$      \n"
                                       + " | $$                          | $$      \n"
                                       + " | $$$$$$$   /$$$$$$  /$$   /$$| $$$$$$$ \n"
                                       + " | $$__  $$ /$$__  $$| $$  | $$| $$__  $$\n"
                                       + " | $$  \\ $$| $$  \\__/| $$  | $$| $$  \\ $$\n"
                                       + " | $$  | $$| $$      | $$  | $$| $$  | $$\n"
                                       + " | $$$$$$$/| $$      |  $$$$$$/| $$  | $$\n"
                                       + " |_______/ |__/       \\______/ |__/  |__/\n";

    // TODO: image null handling
    private final Image bruhImage = new Image(this.getClass().getResourceAsStream("/images/bruh.jpg"));
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image errorImage = new Image(this.getClass().getResourceAsStream("/images/error.png"));

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInputField;
    private Button sendButton;
    private Scene scene;

    /**
     * Initializes the user interface.
     *
     * @param stage         The top-level stage containing all UI elements.
     * @param commandRunner The handler function for the user input.
     */
    public void init(Stage stage, Bruh.CommandRunner commandRunner) {
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
        String greeting = "Hi! How can\n\n" + LOGO.replace(" ", "\u00A0") + "\nhelp you today?\n";
        Label greetingText = new Label(greeting);
        greetingText.setStyle("-fx-font-family: 'monospaced';");
        dialogContainer.getChildren().add(new BruhDialogBox(greetingText, new ImageView(bruhImage)));
    }

    /**
     * Set up the UI elements used by Bruh.
     *
     * @param stage The stage on which the UI elements are set up.
     */
    private void setupUiElements(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        userInputField = new TextField();
        sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();
        scene = new Scene(mainLayout);

        stage.setTitle("Bruh");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(500.0);

        mainLayout.setPrefSize(500.0, 600.0);

        scrollPane.setPrefSize(485.0, 535.0);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.setSpacing(25.0);

        userInputField.setPrefWidth(435.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInputField, 1.0);
        AnchorPane.setBottomAnchor(userInputField, 1.0);

        scrollPane.setContent(dialogContainer);
        mainLayout.getChildren().addAll(scrollPane, userInputField, sendButton);
    }

    /**
     * Set up the event handlers for the UI elements.
     *
     * @param commandRunner The command runner to be attached to events.
     */
    private void setupEventHandlers(Bruh.CommandRunner commandRunner) {
        sendButton.setOnMouseClicked((event) -> handleUserInput(commandRunner));
        userInputField.setOnAction((event) -> handleUserInput(commandRunner));
        dialogContainer.heightProperty().addListener(observable -> scrollPane.setVvalue(1.0));
    }

    /**
     * Handles user input by parsing it into commands & redirecting
     * said commands into the specified command runner.
     *
     * @param commandRunner The command runner to be provided with commands.
     */
    private void handleUserInput(Bruh.CommandRunner commandRunner) {
        String userInput = userInputField.getText();
        Label userText = new Label(userInput);
        try {
            Command command = Parser.parseInputToCommand(userInput);
            commandRunner.runCommand(command);
            Label bruhText = new Label(command.getDescription());
            dialogContainer.getChildren().addAll(
                    new UserDialogBox(userText, new ImageView(userImage)),
                    new BruhDialogBox(bruhText, new ImageView(bruhImage))
            );
            if (command.isExit()) {
                PauseTransition exit = new PauseTransition(Duration.seconds(2.0));
                exit.setOnFinished(event -> Platform.exit());
                exit.play();
            }
        } catch (BruhException e) {
            handleException(userText, e.getMessage());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            handleException(userText, "Please specify a valid task number (use 'list' to view your tasks).");
        } finally {
            userInputField.clear();
        }
    }

    /**
     * Displays the specified error message to the user.
     *
     * @param userText     The user's input which resulted in the error.
     * @param errorMessage The error message to be displayed.
     */
    private void handleException(Label userText, String errorMessage) {
        dialogContainer.getChildren().addAll(
                new UserDialogBox(userText, new ImageView(userImage)),
                new BruhDialogBox(new Label(errorMessage), new ImageView(errorImage))
        );
    }
}
