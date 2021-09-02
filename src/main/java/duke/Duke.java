package duke;

import java.time.format.DateTimeParseException;

import duke.commands.Command;
import duke.exceptions.DukeException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt", "data/");
        tasks = new TaskList(storage.load());
    }
    
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
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



