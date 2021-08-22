package botto;

import botto.command.Command;
import botto.util.Parser;
import botto.util.Storage;
import botto.util.TaskList;
import botto.util.Ui;

/**
 * A task tracking bot
 */
public class Botto {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for a Botto bot
     *
     * @param filePath Filepath of the file that keeps track of the tasks
     */
    public Botto(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (BottoException e) {
            ui.showError(e.getMessage());
        }
    }


    /**
     * Handle the logic of the bot
     */
    private void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (BottoException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }


    /**
     * This method will instantiate the Botto bot
     *
     * @param args sequence of characters (Strings) that are passed to the function.
     */
    public static void main(String[] args) {
        new Botto("data/botto.txt").run();
    }

}
