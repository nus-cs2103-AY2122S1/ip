import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.Ui;
import duke.command.Command;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The Duke program is an application that can store the Tasks you need to do!
 */
public class Duke extends Application {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Constructor for a Duke object.
     *
     * @param filePath the path to store the taskList.txt file in.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.wrapPrint(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Constructor for a Duke object with the default filePath
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data/taskList.txt");
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.wrapPrint(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * The method that runs the Duke application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                ui.printMsg(c.execute(tasks, ui, storage));
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * GUI for Duke, using JavaFX
     *
     * @param stage the Stage to show Scenes on
     */
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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Show welcome message
        dialogContainer.getChildren().add(ui.showWelcomeGui());

        // Handle when Send button is pressed
        sendButton.setOnAction(action -> {
            handleInput(dialogContainer, userInput, scrollPane);
        });

        // Handle when enter key is pressed in userInput
        userInput.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                handleInput(dialogContainer, userInput, scrollPane);
            }
        });
    }

    private void handleInput(VBox container, TextField inputField, ScrollPane scrollPane) {
        Label outputLabel = ui.errorMsgLabel("something went wrong");
        boolean isExit = false;

        try {
            // echo user input
            String fullCommand = inputField.getText();
            Label inputLabel = ui.inputMsgLabel(fullCommand);
            inputLabel.setAlignment(Pos.TOP_RIGHT);
            container.getChildren().add(inputLabel);

            // generate and show program output
            Command c = Parser.parse(fullCommand);
            outputLabel = ui.outputMsgLabel(c.execute(tasks, ui, storage));
            isExit = c.isExit();
        } catch (DukeException e) {
            outputLabel = ui.errorMsgLabel(e.getMessage());
        } finally {
            container.getChildren().add(outputLabel);
        }

        // cleaning up and scrolling down
        inputField.clear();
        scrollPane.vvalueProperty().bind(container.heightProperty());

        // if exit, wait 1 second and close program
        if (isExit) {
            ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
            executorService.schedule(Platform::exit, 1, TimeUnit.SECONDS);
            executorService.schedule(() -> {
                System.exit(0);
            }, 1, TimeUnit.SECONDS);
        }
    }

    /**
     * The main method that makes use of the run method.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
