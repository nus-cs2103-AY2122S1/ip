package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.commands.Command;

/**
 * Represents the chatbot Duke.
 */
public class Duke {

    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke class.
     *
     * @throws IOException when an IO operation fails.
     */
    public Duke() throws IOException {
        this.tasks = new TaskList();
        this.ui = new Ui(new Scanner(System.in));
    }

    /**
     * Method used to run the program.
     */
    public String run(String input) {
        Parser parser = new Parser();
        try {
            Command c = parser.getCommand(input, tasks);
            return c.execute(tasks);
        } catch (DukeException | IOException e) {
            return "***WARNING*** An error has occurred:\n" + e.getMessage();
        }
    }

    public String getResponse(String input) {
        return this.run(input);
    }
}
