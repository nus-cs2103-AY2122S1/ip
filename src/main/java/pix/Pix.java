package pix;

import pix.command.Command;
import pix.exception.PixException;
import pix.parser.Parser;
import pix.storage.Storage;
import pix.task.TaskList;
import pix.ui.Ui;

/**
 * Pix class to run Pix.
 */
public class Pix {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for Pix.
     *
     * @param filePath The file path to access the task list from.
     */
    public Pix(String filePath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            this.taskList = new TaskList(storage.load());
        } catch (PixException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }
}
