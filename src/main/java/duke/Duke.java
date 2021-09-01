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

    /**
     * Constructor for a Duke instance.
     *
     * @param filePath The specified path of the file we will write and load information from.
     */
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

    /**
     * Runs the Duke chatbot.
     *
     * @throws InvalidInputException If the input is deemed invalid.
     */
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
