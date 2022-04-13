package duke;

import java.io.IOException;

import duke.commands.Command;

/**
 * Represents the chatbot, Duke.
 */
public class Duke {

    private TaskList tasks;

    /**
     * Constructor for Duke class.
     *
     * @throws IOException when an IO operation fails.
     */
    public Duke() throws IOException {
        this.tasks = new TaskList();
    }

    /**
     * Method used to run the program.
     */
    public String run(String input) {
        Parser parser = new Parser();
        try {
            Command c = parser.getCommand(input, tasks);
            assert c != null : "Command c should not be null";
            return c.execute(tasks);
        } catch (DukeException | IOException e) {
            return "An error has occurred:\n" + e.getMessage();
        }
    }

    public String getResponse(String input) {
        String response = this.run(input);
        assert (response != null && !response.equals("")) : "Response must not be empty";
        return response;
    }

    public String getWelcomeMsg() {
        return "Hi! How can I help you today?";
    }
}
