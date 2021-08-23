

import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.time.DateTimeException;
import java.time.LocalDateTime;

import static java.lang.Integer.parseInt;

import yoyoexception.YoyoException;
import yoyoexception.YoyoException.YoyoInvalidFormatException;
import yoyoexception.YoyoException.YoyoEmptyCommandException;
import yoyoexception.YoyoException.YoyoTaskIndexException;
import yoyoexception.YoyoException.YoyoIncompleteCommandException;
import yoyoexception.YoyoException.YoyoCommandNotFoundException;


public class Yoyo {
    private TaskList tasks = new TaskList();
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
                c.execute(ui, storage, tasks);

            } catch (YoyoException e) {
                ui.printErrorMessage(e);
            }
        }
    }

    public static void main(String[] args) {
        new Yoyo("data/yoyo.txt").run();
    }


}
