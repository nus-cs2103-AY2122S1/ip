package Pix;

import Pix.command.Command;
import Pix.exception.PixException;

import Pix.parser.Parser;
import Pix.storage.Storage;
import Pix.task.TaskList;
import Pix.ui.Ui;

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

    public static void main(String[] args) {
        System.out.println();
        new Pix("./data/Pix.txt").run();
    }
}