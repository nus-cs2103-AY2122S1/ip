package duke;

import java.nio.file.Path;

import duke.commands.Command;
import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Describes chatbot Duke
 */
public class Duke {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructor for Duke
     *
     * @param filePath the path for the file where data is stored
     */
    public Duke(Path filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.loadData());
        this.ui = new Ui();
    }

    protected String getWelcome() {
        return ui.greet();
    }

    /**
     * Returns Duke's response to a given input
     *
     * @param input the input given by user
     * @return Duke's response
     */
    protected String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showErrorMessage(e);
        }
    }
}
