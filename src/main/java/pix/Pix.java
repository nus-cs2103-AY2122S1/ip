package pix;

import pix.command.Command;
import pix.exception.PixException;
import pix.parser.Parser;
import pix.storage.Storage;
import pix.task.TaskList;
import pix.ui.Ui;

public class Pix {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

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

    /**
     * Reads the text file Pix.Pix.txt and loads the tasks in the Pix.task.Task List from it.
     */
    private void run() {
        ui.displayWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String line = ui.nextCommand();
                Command command = Parser.parse(line);
                String message = command.trigger(this.storage, this.taskList, this.ui);
                ui.displayMessage(message);
                isExit = command.isExit();
            } catch (PixException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Running the application.
     *
     * @param args String.
     */
    public static void main(String[] args) {
        System.out.println();
        new Pix("./data/Pix.txt").run();
    }
}
