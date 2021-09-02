package kermit;

import kermit.command.Command;

/**
 * Driver class for Kermit, a frog that keeps tracks of your tasks.
 */
public class Kermit {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for Kermit.
     *
     * @param filePath File path for where data for task list should be stored.
     */
    public Kermit(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (KermitException e) {
            ui.getLoadingError();
            taskList = new TaskList();
        }
    }

    public String getIntroMessage() {
        return ui.getIntroMessage();
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(taskList, ui, storage);
        } catch (KermitException e) {
            return ui.getErrorMessage(e.getMessage());
        }
    }
}
