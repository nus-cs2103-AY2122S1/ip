package duke;
import duke.commands.Command;
import java.io.FileNotFoundException;
import duke.exceptions.DukeException;
public class Duke {
    private final Parser parser;
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Duke(String filePath) {
        parser = new Parser();
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    protected void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.err.println(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

}
