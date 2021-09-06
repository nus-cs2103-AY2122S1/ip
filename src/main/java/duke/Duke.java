package duke;

import java.io.IOException;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.exceptions.NoSuchCommandException;
import duke.exceptions.NoSuchTaskException;

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
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (NoSuchCommandException | NoSuchTaskException | IOException ex) {
                System.out.println("Something went wrong");
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

