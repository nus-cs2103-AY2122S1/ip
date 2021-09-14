package duke;

import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final String FILE_PATH = "data/dukeData.txt";

    /**
     * Initialise the components of the program
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Handles the input data
     *
     * @param input the input message by user
     * @return String to be shown to user
     */
    public String getResponse(String input) {
        if (input.equals("start")) {
            return Ui.startBot();
        }
        if (input.equals("bye")) {
            return ui.endBot();
        }
        Parser p = new Parser(tasks, storage, ui);
        return p.parse(input);
    }
}
