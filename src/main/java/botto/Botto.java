package botto;

import botto.command.Command;
import botto.util.Parser;
import botto.util.Storage;
import botto.util.TaskList;
import botto.util.Ui;

public class Botto {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Botto(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (BottoException e) {
            ui.showError(e.getMessage());
        }
    }


    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
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

    public static void main(String[] args) {
        new Botto("data/botto.txt").run();
    }

}
