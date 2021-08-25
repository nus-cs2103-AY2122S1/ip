package duke;

import duke.command.Command;
import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

import java.util.Objects;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.loadDataFile());
    }

    public void run() {
        ui.greet();
        boolean exit = false;
        while(!exit) {
            try {
                Command c = Parser.parseInputs(ui.nextCommand());
                if (Objects.isNull(c)) {
                    ui.clarify();
                } else {
                    c.execute(storage, taskList, ui);
                    exit = c.isExit();
                    ui.promptNext();
                }
            } catch (DukeException e) {
                ui.respond(e.getMessage());
            }
        }
        ui.respond("Ooooh yeah! Can do!");
    }
}
