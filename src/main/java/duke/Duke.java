package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Main class to run the program.
 */
public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Parser parser;
    private Ui ui;

    /**
     * Constructs a Duke object initialised with a path to hard drive.
     *
     * @param saveFile The given hard drive path.
     */
    public Duke(Path saveFile) {
        ui = new Ui();
        storage = new Storage(saveFile, ui);
        tasks = new TaskList(storage, ui);
        parser = new Parser(tasks, ui);
    }

    /**
     * Main driver that starts the program.
     */
    public void run() {
        ui.firstWelcome();
        boolean cont = true;
        while (cont) {
            String c = ui.readCommand();
            cont = parser.parse(c);
        }
    }

    public static void main(String[] args) {
        new Duke(Paths.get("data", "test.txt")).run();
    }


}
