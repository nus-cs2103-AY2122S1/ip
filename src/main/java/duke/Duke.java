package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;

import java.io.IOException;
import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private TaskList tasks;

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

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
