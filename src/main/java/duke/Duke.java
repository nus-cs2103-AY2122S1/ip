package duke;

import java.time.format.DateTimeParseException;

import duke.commands.Command;
import duke.exceptions.DukeException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke that initialises UI, Storage and Tasklist upon start. 
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt", "data/");
        tasks = new TaskList(storage.load());
    }

    /**
     * Parses user input to create respective command and execute it.
     * @param input User input from GUI.
     * @return String response to be displayed in GUI.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return "OOPS! Please input date in this format: yyyy-mm-dd";
        }
    }
}



