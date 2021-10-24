package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * This class encapsulates the Duke app.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;

    public Duke() {
        this.storage = new Storage();
        this.tasks = new TaskList(this.storage.load());
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String response = command.execute(this.tasks);
            this.storage.save(this.tasks);
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}




