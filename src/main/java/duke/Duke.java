package duke;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * The Duke Application.
 */
public class Duke extends Application {
    private final String dataFilePath = "./data/tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {}

    @Override
    public void start(Stage stage) {
        ui = new Ui(stage);
        storage = new Storage(dataFilePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }

        ui.showWelcome();
        ui.getSendButton().setOnMouseClicked((event) -> {
            handleUserInput(ui.readCommand());
        });
        ui.getUserInput().setOnAction((event) -> {
            handleUserInput(ui.readCommand());
        });
    }

    private void handleUserInput(String fullCommand) {
        try {
            ui.showFullCommand(fullCommand);
            ui.clearInput();
            Command c = Parser.parse(fullCommand);
            assert !Objects.isNull(c) : "Should always return a command, if no error thrown.";
            c.execute(tasks, storage, ui);
            if (c instanceof ExitCommand) {
                Platform.exit();
            }
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}
