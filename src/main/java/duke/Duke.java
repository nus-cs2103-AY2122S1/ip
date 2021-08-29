package duke;

import duke.task.TaskList;
import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.exception.DukeException;

import java.io.*;
import java.nio.file.Paths;

/**
 * Class that handles the behaviour of the bot in response to user inputs
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static final String FILE_PATH = String.valueOf(Paths.get(System.getProperty("user.home"), "data", "dukeFile.txt"));

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            File file = new File(filePath);
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand, tasks);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
            System.out.println("Caught the exception");
            System.out.print("Exception occurred: " + e);
            }
        }
    }

    /**
     * Method to call when user wishes to see a list of all events
     */
    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }
}