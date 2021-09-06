package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.exceptions.NoSuchCommandException;
import duke.exceptions.NoSuchTaskException;

import java.io.IOException;
import java.util.ArrayList;
/**
 * Main Class that calls the other components to put the whole system together
 */

public class Duke {

    private Storage storage;
    private Tasklist tasks;
    private Ui ui;

    /**
     * Constructor for Duke class
     *
     * @param filePath file path to save the tasklist to
     */

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.fileToTasklist();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new Tasklist();
        }
    }

    /**
     * Logic for running Duke
     */

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (NoSuchCommandException | NoSuchTaskException | IOException ex) {
            } finally {
                //ui.showLine();
            }
        }
    }

    /**
     * Main Method that creates new Duke and runs it
     */

    public static void main(String[] args) {
        new Duke("\\tasks.txt").run();
    }
}

