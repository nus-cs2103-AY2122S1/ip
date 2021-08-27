package duke;

import duke.task.TaskList;

import java.io.IOException;
import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final String FILE_PATH = "data/dukeData.txt";

    /**
     * Initialise the components of the program
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Runs program
     */
    public void run() {
        ui.startBot();
        Parser p = new Parser(tasks, storage);
        p.parse();
        ui.endBot();
    }

    /**
     * Initialise and start program
     *
     * @param args arguments passed by the command line
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
