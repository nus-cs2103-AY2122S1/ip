package duke;

import duke.command.Command;
import duke.exception.InvalidInputException;
import duke.util.Ui;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;

import java.io.*;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws InvalidInputException {

        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            String input = ui.readCommand();
            Command c = Parser.parseCommand(input);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        }

    }

    public static void main(String[] args) throws InvalidInputException {
        new Duke("./data/tasks.json").run();
    }
}
