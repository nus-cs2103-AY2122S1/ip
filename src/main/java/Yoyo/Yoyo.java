package Yoyo;

import Yoyo.command.Command;
import Yoyo.core.Parser;
import Yoyo.core.Storage;
import Yoyo.core.Ui;
import Yoyo.exception.YoyoException;
import Yoyo.task.TaskList;

public class Yoyo {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

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
