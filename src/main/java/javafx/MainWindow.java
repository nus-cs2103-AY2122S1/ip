package javafx;

import duke.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    private Ui dukeUi;
    private Stage primaryStage;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Configures the main window when launching.
     *
     * @param dukeUi the dukeUi that power the chat bot.
     * @param primaryStage The stage that hosts this window.
     */
    public void setMainWindow(Ui dukeUi, Stage primaryStage) {
        this.dukeUi = dukeUi;
        this.primaryStage = primaryStage;
        this.displayStartMessage();
    }

    /**
     * Displays a start message to the user.
     */
    public void displayStartMessage() {
        this.dialogContainer.getChildren().addAll(
                DialogBox.getStartDialog()
        );
    }

    /**
     * Takes a user input and evaluates it accordingly. It is triggered
     * when the user press the send button on the GUI or presses enter
     * on his keyboard.
     */
    @FXML
    public void handleUserInput() {
        String input = userInput.getText();
        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input),
                DialogBox.getDukeDialog(getResponse(input))
        );
        userInput.clear();

        // Checks to see if the user input given is to close the chat bot.
        if (this.dukeUi.isExit()) {
            this.closeDuke();
        }
    }

    private void closeDuke() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> System.exit(0),
                1, TimeUnit.SECONDS);
    }

    private String getResponse(String input) {
        return this.dukeUi.readUserInput(input.strip());
    }
}
