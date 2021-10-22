package duke;

import duke.task.TaskList;
import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.exception.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

/**
 * Class that handles the behaviour of the bot in response to user inputs
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    private static final String FILE_PATH = String.valueOf(Paths.get("data", "dukeFile.txt"));

    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            File file = new File(FILE_PATH);
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        String res;
        try {
            Command c = Parser.parse(input, tasks);
            res = c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return "Caught the exception" + e;
        }
        return res;
    }

    public String getUiWelcome() {
        return this.ui.showWelcome();
    }
}