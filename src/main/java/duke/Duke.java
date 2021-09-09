package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

import duke.task.TaskList;

/**
 * Duke.
 * @author Thomas Hogben
 */
public class    Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Provide a path to the directory for Duke to save in.
     * Duke will create it if it does not exist.
     *
     * @param savePath
     */
    public Duke(Path savePath) {
        ui = new Ui();
        storage = new Storage(ui, savePath);
        try {
            taskList = new TaskList(ui, storage.load());
        } catch (DukeException e) {
            ui.display(e);
            taskList = new TaskList(ui);
        }
    }

    /**
     * Runs the Duke bot.
     */
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
