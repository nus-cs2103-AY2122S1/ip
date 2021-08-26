package yoyo;

import yoyo.command.Command;
import yoyo.core.Parser;
import yoyo.core.Storage;
import yoyo.core.Ui;
import yoyo.exception.YoyoException;
import yoyo.task.TaskList;

public class Yoyo {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

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

    public static void main(String[] args) {
        new Yoyo("data/yoyo.txt").run();
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


}
