package duke;

import duke.command.Command;

import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private TaskList tasks;

    public Duke(String filepath) {
        try {
            storage = new Storage(filepath);
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            tasks = new TaskList(new ArrayList<>());
        }
    }

    String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
