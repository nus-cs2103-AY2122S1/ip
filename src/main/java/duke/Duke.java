package duke;

import duke.command.Command;
import duke.task.TaskList;

import java.io.FileNotFoundException;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;
    private boolean isExit;

    /**
     * Constructor for a Duke instance.
     * @param filePath file path for file to save/load save data.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.isExit = false;
        try {
            taskList = storage.load();
        } catch (FileNotFoundException e) {
            taskList = new TaskList();
        }
        this.parser = new Parser(this, taskList, storage, ui);
    }

    public String getResponse(String input) {
        if (!isExit) {
            Command cmd = parser.parse(input);
            if (cmd != null) {
                cmd.execute();
            }
        } else {
            System.exit(0);
        }

        return ui.getMessage();
    }

    /**
     * Triggers the exit boolean to stop the Duke from running.
     */
    public void triggerExit() {
        isExit = true;
    }
}
