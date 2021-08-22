import command.Command;
import exception.PixException;

import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class Pix {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

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
     * Reads the text file Pix.txt and loads the tasks in the task.Task List from it.
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

    public static void main(String[] args) {
        new Pix("data/Pix.txt").run();
    }
}