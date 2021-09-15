package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Duke is message bot to help user to keep track of task.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke object.
     *
     * @param filePath Filepath of the data.txt that store.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.creatingLoadingErrorMessage();
            tasks = new TaskList();
        }
    }

    /**
     * Returns Duke's response from user's input.
     *
     * @param input User's input.
     * @return Duke's response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.execute(tasks, ui, storage);;
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }


}
