package catobot;

import catobot.command.Command;
import catobot.command.Parser;
import catobot.exception.BotException;
import catobot.exception.EmptyCommandException;
import catobot.item.TaskList;

import java.io.FileNotFoundException;

public class Catobot {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Catobot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (EmptyCommandException | FileNotFoundException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (BotException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Catobot("./data/Catobot.txt").run();
    }

}
