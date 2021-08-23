package duke;

import duke.command.Command;
import duke.exceptions.UnknownException;
import duke.util.TaskList;
import duke.util.Parser;
import duke.util.Ui;
import duke.util.Storage;

public class Duke {
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;

    protected static final String FILE_URL = "data/Duke.txt";

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadStorage());
    }

    public static void main(String[] args) {
        new Duke(FILE_URL).run();
    }

    /**
     * Runs the program with the correct components and storage file set-ups.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readNextLine();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (UnknownException e) {
                ui.displayError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}

