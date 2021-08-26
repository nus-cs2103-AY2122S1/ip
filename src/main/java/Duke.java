import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.Ui;

import tasks.TaskList;

import java.io.IOException;

/**
 * The Duke program implements a chatbot called Duke that
 * supports queries such as creating, marking and deleting tasks.
 *
 * @author Chen Hsiao Ting
 * @version 1.0
 * @since 2021-08-13
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
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    /**
     * Runs the Duke chatbot program until user input the bye command.
     */
    public void run() {
        Ui.welcome();
        Parser.startDuke(tasks);
        Storage.saveList();
    }

    /**
     * Starts the Duke chatbot program
     *
     * @param args input path for the data file
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}