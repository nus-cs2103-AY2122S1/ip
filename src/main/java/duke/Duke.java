package duke;

import duke.command.Command;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit = false;

    /**
     * Constructor for class Duke
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Starts the process of Duke responding
     *
     * @param input the user input
     * @return the String of Duke's response
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            this.isExit = c.isExit();
        } catch (DukeException e) {
            ui.reply(e.getMessage());
        }
        return ui.getCurrentResponse();
    }

    /**
     * Returns whether Duke is meant to exit
     *
     * @return boolean containing whether Duke is meant to exit
     */
    public boolean isDukeExit() {
        return this.isExit;
    }
}
