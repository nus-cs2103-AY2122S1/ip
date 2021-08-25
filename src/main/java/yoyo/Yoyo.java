package yoyo;

import yoyo.command.Command;
import yoyo.core.Parser;
import yoyo.core.Storage;
import yoyo.core.Ui;
import yoyo.exception.YoyoException;
import yoyo.task.TaskList;

public class Yoyo {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructor for Yoyo program.
     *
     * @param filePath File path to the file to be loaded.
     */
    public Yoyo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (YoyoException e) {
            ui.printErrorMessage(e);
            tasks = new TaskList();
        }
    }

    /**
     * Main method to start running the Yoyo program.
     */
    private void run() {
        ui.greetUser();
        boolean shouldRun = true;
        while (shouldRun) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, storage, ui);
                shouldRun = c.shouldContinueProgram();
            } catch (YoyoException e) {
                ui.printErrorMessage(e);
            }
        }
    }

    public static void main(String[] args) {
        new Yoyo("data/yoyo.txt").run();
    }


}
