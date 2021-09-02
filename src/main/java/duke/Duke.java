package duke;

import java.util.ArrayList;

import duke.command.Command;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Class used to start ChatBot.
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor to initialize Duke.
     */
    public Duke() {
        ui = new Ui(this);
        storage = new Storage();
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            taskList = new TaskList(new ArrayList<>());
            ui.showError(e);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        ui.start();
    }

    protected void run(String fullCommand) {
        try {
            Command c = Parser.parse(fullCommand);
            c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            ui.showError(e);
        }
    }

    public static void main(String[] args) { }
}
