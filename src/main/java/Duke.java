import duke.command.Command;

import duke.error.DukeException;

import duke.general.Parser;
import duke.general.Storage;
import duke.general.Tasklist;
import duke.general.Ui;

/**
 * Chatbot that helps you form a task list
 */

public class Duke {
    private Storage storage;
    private Tasklist tasks;
    private Ui ui;
    private Parser parser;


    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new Tasklist(storage.loadSave());
        parser = new Parser();
    }

    public void run() {
        ui.showWelcome();
        while (ui.getLoop()) {
            try {
                String fullCommand = ui.readInput();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, storage, ui);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
