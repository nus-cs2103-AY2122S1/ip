package duke;

import duke.commands.Command;

/**
 * This class represents the Duke chatbot. It is the main class from which the chatbot is run.
 */
public class Duke {
    private TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructs a Duke object with a UI, Storage and TaskList with stored Tasks.
     */
    public Duke() {
        ui = new Ui();
        this.storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Executes command from parsing user input.
     * Returns message to be shown to user.
     * 
     * @param input User input.
     * @return Message to be shown to user.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }
}
