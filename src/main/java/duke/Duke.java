package duke;

import duke.command.CommandManager;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;
import duke.exception.DukeException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main class for the bot.
 */
public class Duke extends Application {
    private final DukeList list;
    private final CommandManager commandManager;
    private final UserInterface ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Creates a duke application.
     */
    public Duke() {
        this.list = new DukeList();
        this.commandManager = new CommandManager();
        this.ui = new UserInterface();
    }

    public static void main(String[] args) {
        new Duke().initialize();
    }

    private void initialize() {
        this.ui.greet();

        // Duke commands work with a registry so that add-ons can be developed with
        // commands simply registered like so
        this.commandManager.registerCommands(new ListCommand(this.list, this.ui), new DoneCommand(this.list, this.ui),
                new ToDoCommand(this.list, this.ui), new EventCommand(this.list, this.ui),
                new DeadlineCommand(this.list, this.ui), new DeleteCommand(this.list, this.ui),
                new FindCommand(this.list, this.ui));

        this.run();
    }

    private void run() {
        String input = "";
        try {
            input = this.ui.getInput();
        } catch (DukeException e) {
            this.ui.showError(e);
        }
        if (input.equals("bye")) {
            terminate();
            return;
        } else {
            try {
                this.commandManager.parseInput(input);
            } catch (DukeException e) {
                this.ui.showError(e);
            }
        }
        run();
    }

    private void terminate() {
        this.ui.farewell();
    }

    @Override
    public void start(Stage primaryStage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        primaryStage.setScene(scene);
        primaryStage.show();
        // F
        primaryStage.setTitle("Duke");
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(600.0);
        primaryStage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        // F
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

}
