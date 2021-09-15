package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

import duke.task.TaskList;

/**
 * Duke.
 * @author Thomas Hogben
 */
public class Duke {
    private Parser parser;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage(ui);
        try {
            taskList = new TaskList(ui, storage.load());
        } catch (DukeException e) {
            //ui.display(e);
            taskList = new TaskList(ui);
        }
        parser = new Parser(taskList, storage, ui);
    }

    public String getResponse(String input) {
        return parser.parse(input);
    }

    /*
    public static void main(String[] args) {
        Path savePath = Paths.get("data");
        new Duke(savePath).run();
    } */
}
