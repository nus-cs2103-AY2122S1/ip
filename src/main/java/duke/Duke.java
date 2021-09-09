package duke;

import duke.command.Command;
import duke.command.ExitCommand;
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
     * Parses the input from the user and returns a string response from Duke.
     *
     * @param input The given user String input.
     * @return The String output from duke.
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

    /**
     * Greets the user.
     *
     * @return The greeting message
     */
    public String greet() {
        return ui.greet();
    }

    public void exit() {
        new ExitCommand().execute(taskList, ui, storage);
    }
}