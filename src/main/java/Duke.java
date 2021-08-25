import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.IOException;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
        parser = new Parser(taskList, ui, storage);
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine();
            isExit = parser.isExit(fullCommand);
            if(!isExit) {
                parser.parse(fullCommand);
            }
        }
        ui.showGoodBye();
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/tasks.txt").run();
    }
}
