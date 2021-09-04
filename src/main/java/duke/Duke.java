package duke;

import duke.command.Command;
import duke.exception.DukeException;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    @SuppressWarnings("unused")
    public Duke() {
    }

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.load());
        this.ui = new Ui();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String output;
        try {
            Command c = Parser.parse(input);
            output = c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            output = e.getMessage();
        }
        return output;
    }

    public String greet() {
        return ui.greet();
    }
}