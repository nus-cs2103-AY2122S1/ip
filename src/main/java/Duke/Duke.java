package Duke;

import Duke.command.Command;
import Duke.exception.DukeException;

public class Duke {

    private Storage storage;
    private TaskList tasks;

    public Duke() {
        this.storage = new Storage("data");
        this.tasks = new TaskList(this.storage.load());
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String response;
        try {
            Command command = Parser.parse(input);
            response = command.execute(this.tasks);
            this.storage.save(this.tasks);
        } catch (DukeException e) {
            return e.getMessage();
        }
        //s
        return response;
    }
}




