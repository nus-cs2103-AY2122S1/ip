package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;

import java.io.IOException;
import java.util.ArrayList;

/** The class that ties all the operations of Parser, TaskList, Storage together */
public class Duke {

    private Storage storage;
    private TaskList tasks;

    /**
     * A constructor for Duke
     *
     * @param filepath The file path for which the data of Duke resides
     * @throws IOException When Duke cannot be initialised due to failure of operating on the file
     */
    public Duke(String filepath) throws IOException {

        assert filepath != null;

        try {
            storage = new Storage(filepath);
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            tasks = new TaskList(new ArrayList<>());
            storage.createNewFile();
        }
    }

    /**
     *
     * @param userInput The user's input
     * @return A response based on the user's input
     */
    public String getResponse(String userInput) {
        try {
            Command c = Parser.parse(userInput);
            return c.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
