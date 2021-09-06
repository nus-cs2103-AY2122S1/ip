import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Duke.
 * @author Thomas Hogben
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(Path savePath) {
        this.ui = new Ui();
        this.storage = new Storage(ui, savePath);
        try {
            this.taskList = new TaskList(ui, storage.load());
        } catch (DukeException e) {
            ui.display(e);
            this.taskList = new TaskList(ui);
        }
    }

    public void run() {
        Parser parser = new Parser(taskList, storage, ui);
        ui.init();
        parser.parse();
        ui.exit();
    }

    public static void main(String[] args) {
        Path savePath = Paths.get("data");
        new Duke(savePath).run();
    }
}
