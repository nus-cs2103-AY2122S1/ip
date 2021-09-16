package duke;

import java.io.IOException;

import duke.exception.DukeException;
import duke.storage.ArchiveStorage;
import duke.storage.MainStorage;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Ui;

/**
 * This class encapsulates the Duke application.
 */
public class Duke {
    private final Ui ui;
    private final MainStorage mainStorage;
    private final ArchiveStorage archiveStorage;
    private TaskList tasks;

    /**
     * Constructor loads the save file from the specified filepath.
     *
     * @param filepath The path to the save file (if any).
     */
    public Duke(String filepath) {
        ui = new Ui();
        mainStorage = new MainStorage(filepath);
        archiveStorage = new ArchiveStorage("data/archive.txt");

        try {
            tasks = new TaskList(mainStorage.load());
            assert tasks != null;
        } catch (IOException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Returns Duke's response to the input string.
     *
     * @param input The input into Duke.
     * @return Duke's response.
     */
    public String handleInput(String input) {
        String response;
        try {
            response = Parser.parse(input).execute(tasks, mainStorage, archiveStorage);
            assert response != null;
        } catch (DukeException e) {
            response = e.getMessage() + "\n";
        }
        return response;
    }
}
