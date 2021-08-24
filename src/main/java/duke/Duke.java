package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            this.ui.showLoadingError();
            this.taskList = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        Command command;
        boolean isExited = false;
        do {
            try {
                String input = ui.readCommand();
                command = Parser.parseNext(input);
                command.execute(taskList, ui, storage);
                isExited = command.hasExited();
            } catch (DukeException e) {
                ui.printOut(e.getMessage());
            }
        }
        while (!isExited);
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }
}
