package duke;

import java.io.FileNotFoundException;

import duke.commands.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * This is the constructor for the Duke object.
     *
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("Invalid file path.");
            e.printStackTrace();
        }
    }

    /** This method starts the Duke chat bot. */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand, tasks);
            c.execute(tasks, ui, storage);
            if (c.isExit()) {
                break;
            }
        }
    }


    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
