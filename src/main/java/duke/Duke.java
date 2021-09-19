package duke;

import java.io.IOException;

import duke.command.Command;
import duke.data.Parser;
import duke.data.Storage;
import duke.exceptions.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The Duke programme implements a bot that help users to record the tasks they have.
 */
public class Duke {
    private static final String STORAGE_DIRECTORY_PATH = "data/duke.txt";

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Initialises a Duke object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(STORAGE_DIRECTORY_PATH);

        try {
            boolean isNewDataFileCreated = storage.createNewDataFile();
            if (!isNewDataFileCreated) {
                taskList = new TaskList(storage.load());
            } else {
                taskList = new TaskList();
            }
        } catch (IOException | DukeException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a string representing the response from Duke based on the user input.
     *
     * @param input the user input
     * @return a response string from Duke
     */
    public String getResponse(String input) {
        String response;
        Parser p = new Parser();
        try {
            Command c = p.parse(input);
            response = c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            response = ui.showErrorMessage(e.getMessage());
        }
        return response;
    }
}
