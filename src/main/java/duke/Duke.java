package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.exception.DukeException;

import java.util.concurrent.TimeUnit;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private boolean exit;

    @SuppressWarnings("unused")
    public Duke() {
    }

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.load());
        this.ui = new Ui();
        exit = false;
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
            exit = c.isExit();
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

    /**
     * Determines whether the user wants to close duke.
     *
     * @return Whether the user wants to close Duke.
     */
    public boolean isExit() {
        return exit;
    }

    /**
     * Closes Duke by executing an ExitCommand.
     */
    public void exit() {
        new ExitCommand().execute(taskList, ui, storage);
    }
}