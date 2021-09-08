package duke;

import duke.command.Command;

public class Duke {

    private final TaskList tasks;

    /**
     * Constructs a Duke object.
     */
    public Duke() {
        Storage storage = new Storage("data/tasks.txt");
        tasks = new TaskList(storage);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {

        assert input != null;

        try {
            Command command = Parser.parse(input);
            return command.execute(tasks);
        } catch (DukeException e) {
            // Get duke.Duke to say out the error
            return e.getMessage();
        }
    }
}
