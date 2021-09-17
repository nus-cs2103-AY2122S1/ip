package duke.ui;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import duke.Parser;
import duke.Storage;
import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * The Ui class encapsulates all the methods for user input and printing to the terminal.
 */
public class Ui {

    private static final String APPLICATION_TITLE = "Bob";
    private static final Double APPLICATION_HEIGHT = 600.0;
    private static final Double APPLICATION_WIDTH = 400.0;
    private static final String WELCOME_MSG = "Hello! I'm Bob\nWhat can I do for you?";
    private static final long EXIT_DELAY = 1;

    private DukeScrollPane scrollPane;
    private DialogContainer dialogContainer;
    private UserInput userInput;
    private SendButton sendButton;
    private MainLayout mainLayout;
    private Scene scene;

    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for a Ui object.
     *
     * @param storage the given Storage.
     * @param tasks the given TaskList.
     */
    public Ui(Storage storage, TaskList tasks) {
        this.storage = storage;
        this.tasks = tasks;


        dialogContainer = new DialogContainer();
        scrollPane = new DukeScrollPane(dialogContainer);
        userInput = new UserInput();
        sendButton = new SendButton();
        mainLayout = new MainLayout(APPLICATION_HEIGHT, APPLICATION_WIDTH);

        mainLayout.setAnchors(scrollPane, sendButton, userInput);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        scene = new Scene(mainLayout);
    }

    /**
     * Launches the GUI for Duke
     * @param stage the given Stage
     */
    public void launchGui(Stage stage) {
        prepareStage(stage);
        setButtons();
        showWelcome();
    }

    /**
     * Sets the send button and enter keypress to run the handleInput method when pressed
     */
    private void setButtons() {
        sendButton.setOnAction(action -> handleInput());

        // Handle when enter key is pressed in userInput
        userInput.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                handleInput();
            }
        });
    }

    /**
     * Configures given stage.
     * @param stage the given Stage
     */
    private void prepareStage(Stage stage) {
        stage.setTitle(APPLICATION_TITLE);
        stage.setResizable(false);
        stage.setMinHeight(APPLICATION_HEIGHT);
        stage.setMinWidth(APPLICATION_WIDTH);

        stage.setScene(scene);
        stage.show();
    }

    private void handleInput() {

        try {
            // echo user input
            String fullCommand = userInput.getText();
            dialogContainer.sendInputMessage(fullCommand);

            // generate and show program output
            Command c = Parser.parse(fullCommand);
            dialogContainer.sendOutputMessage(c.execute(tasks, storage));
            checkExit(c.isExit());
        } catch (DukeException e) {
            dialogContainer.sendErrorMessage(e.getMessage());
        } finally {
            userInput.clear();
        }

    }

    /**
     * Checks isExit, waits the specified delay before exiting the program.
     * @param isExit exit boolean
     */
    private void checkExit(boolean isExit) {
        if (isExit) {
            ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
            executorService.schedule(Platform::exit, EXIT_DELAY, TimeUnit.SECONDS);
            executorService.schedule(() -> System.exit(0), 1, TimeUnit.SECONDS);
        }
    }

    /**
     * Shows the welcome message on the GUI
     */
    public void showWelcome() {
        dialogContainer.sendOutputMessage(WELCOME_MSG);
    }
}
