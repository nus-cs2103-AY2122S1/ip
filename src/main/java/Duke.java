

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.Ui;
import tasks.TaskList;

import java.io.IOException;

/**
 * Duke class encapsulate a chatbot service.
 * It supports queries such as creating, marking and deleting tasks.
 *
 * @author: Chen Hsiao Ting
 */

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTask());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        Ui.welcome();
        Parser.startDuke(tasks);
        Storage.saveList();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}