package duke;

import duke.command.Command;
import duke.task.TaskList;
import javafx.application.Platform;

public class Duke {

    private static final String FILE_PATH = "data/data.txt";

    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a duke chat bot.
     *
     */
    public Duke() {
        this.storage = new Storage(FILE_PATH);
        try {
            this.taskList = new TaskList(storage.loadData());
        } catch (DukeException e) {
            ui.getDukeError(e);
        }
        this.ui = new Ui();
    }

    protected String getResponse(String input) {
        String response = "";
        try {
            Command c = Parser.parseCommand(input);
            if (c.isExit()) {
                Platform.exit();
            }
            response = c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            response = ui.getDukeError(e);
        }
        return response;
    }

}


