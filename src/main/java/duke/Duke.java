package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.exceptions.NoSuchCommandException;
import duke.exceptions.NoSuchTaskException;

import java.io.IOException;
import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private Tasklist tasks;
    private Ui ui;

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

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                //#ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (NoSuchCommandException | NoSuchTaskException | IOException ex) {
                //ui.showError(e.getMessage());
            } finally {
                //ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("\\tasks.txt").run();
    }
}

