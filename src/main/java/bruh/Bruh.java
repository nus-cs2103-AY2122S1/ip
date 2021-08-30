package bruh;

import bruh.command.Command;
import bruh.storage.Storage;
import bruh.tasklist.TaskList;
import bruh.ui.Ui;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main class for the Bruh chatbot program.
 */
public class Bruh extends Application {
    private static final String STORAGE_PATH = "/output/tasklist.txt";

    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;
    private final CommandRunner commandRunner;

    /**
     * A constructor for the Bruh chatbot.
     */
    public Bruh() {
        this.ui = new Ui();
        this.storage = new Storage(STORAGE_PATH);
        this.taskList = storage.loadTaskList();
        this.commandRunner = command -> command.execute(taskList, ui, storage);
    }

    @Override
    public void start(Stage primaryStage) {
        ui.init(primaryStage, commandRunner);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        storage.saveToDisk(taskList);
    }

    /**
     * Represents a runner class which provides some additional context
     * required to execute a command.
     */
    @FunctionalInterface
    public interface CommandRunner {
        /**
         * Runs the specified command with the additional context required.
         *
         * @param command The specified command to be run.
         */
        void runCommand(Command command);
    }
}
