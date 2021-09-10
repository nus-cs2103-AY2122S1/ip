package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Main;
import duke.ui.MainWindow;
import duke.ui.Ui;
import javafx.application.Application;

/**
 * A class for Duke program.
 */
public class Duke {
    private final Ui ui;
    private TaskList taskList;
    private final Storage storage;
    private boolean isExit;

    public Duke(MainWindow mw) {
        this("data/duke.txt", mw);
    }

    /**
     * Returns a Duke object.
     *
     * @param filePath The path of the file of storage.
     * @param mw The JavaFX MainWindow for GUI.
     */
    public Duke(String filePath, MainWindow mw) {
        ui = new Ui(mw);
        storage = new Storage(filePath);

        try {
            taskList = TaskList.deserialize(storage.load());
            ui.sayLoadingSuccess();
        } catch (DateTimeParseException | IllegalArgumentException | IOException e) {
            ui.sayLoadingFail();
            taskList = new TaskList();
        }
    }

    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Processes the response of user input.
     *
     * @param response User's response.
     */
    public void process(String response) {
        try {
            Command c = Parser.parse(response);
            assert c != null : "Parser.parse should never return a null command";
            c.execute(taskList, ui, storage);
            this.isExit = c.isExit();
        } catch (IOException | DukeException e) {
            ui.sayError(e.getMessage());
        }
    }

    /**
     * Boots the Duke.
     */
    public void boot() {
        ui.sayGreet();
        ui.sayHelp();
    }

    /**
     * Shuts down the Duke.
     */
    public void shutDown() {
        ui.sayGoodBye();
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
