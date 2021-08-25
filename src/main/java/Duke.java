import duke.parser.Parser;
import duke.task.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

import java.lang.String;

public class Duke {
    private static final String LOCATION = "./data/duke.txt";
    private TaskList tasks;
    private Storage storage;
    private Parser parser;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadData());
    }

    public void run() {
        boolean isRunning = true;
        ui.intro();
        ui.greet();
        parser = new Parser(tasks, storage);
        while (isRunning) {
            isRunning = parser.parseCommand();
        }
    }

    public static void main(String[] args) {
        new Duke(LOCATION).run();
    }
}
