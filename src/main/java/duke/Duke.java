package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Main class of Duke chatbot. Contains the main method that initialises Duke
 * and starts the chatbot.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasklist;
    private Parser parser;

    /**
     * Constructor for Duke.
     *
     * @param filepath The filepath of the save file.
     */
    public Duke(Path filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasklist = new TaskList(storage.load());
        } catch (DukeException e) {
            tasklist = new TaskList();
        }
        parser = new Parser(ui, storage, tasklist);
    }

    /**
     * Runs the program.
     */
    public void run() {
        this.ui.showWelcome();
        boolean end = false;
        while (!end) {
            String input = ui.readInput();
            end = parser.process(input);

        }
    }

    public static void main(String[] args) {
        Path filePath = Paths.get("src", "main", "data", "duke.txt");
        new Duke(filePath).run();
    }
}
