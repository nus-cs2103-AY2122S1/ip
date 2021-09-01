package duke;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import javafx.application.Application;
import javafx.stage.Stage;

public class Duke extends Application {
    private final String DEFAULT_FILEPATH = "data/tasks.txt";

    private final Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke.
     * No parameter constructor fallbacks to DEFAULT_FILEPATH.
     * Loads the tasks from the file.
     * If fail, start with an empty TaskList.
     */
    public Duke() {
        storage = new Storage(DEFAULT_FILEPATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    @Override
    public void start(Stage stage) {
        ui = new Ui(stage, storage, tasks);

        // Initialize the UI and start the program
        ui.init();
    }
}