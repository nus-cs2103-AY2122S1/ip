package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * A chat-bot called Naruto that acts as a task list.
 */
public class Duke extends Application {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for the Duke chat-bot.
     *
     * @param persistedData the relative path to the persisted data starting from the project directory.
     */
    public Duke(String persistedData) {
        ui = new Ui();
        storage = new Storage(persistedData);
        tasks = new TaskList(storage.loadPersistedData());
    }

    /**
     * Constructor for the Duke chat-bot.
     */
    public Duke() {
        String persistedData = "data/duke.txt";
        ui = new Ui();
        storage = new Storage(persistedData);
        tasks = new TaskList(storage.loadPersistedData());
    }

    /**
     * Initializes and starts the chat-bot for operation/interaction.
     */
    public void run() {
        boolean toExit = false;
        ui.showWelcome();
        while (!toExit) {
            String fullCommand = ui.readCommand();
            ui.showLines();
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                toExit = c.isExitCommand();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLines();
            }
        }
    }
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    @Override
    public void init() {
        int i = 1 + 1;
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Naruto Chat-bot!");
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
