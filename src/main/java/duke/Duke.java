package duke;

import duke.command.Command;
import duke.exception.DukeException;

import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.FileNotFoundException;

public class Duke {
    private static final String DEFAULT_PATH = "./data/duke.txt";
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke() {
        this(new Ui(), new Storage(DEFAULT_PATH), new TaskList());
    }

    public Duke(String filePath) {
        try {
            ui = new Ui();
            storage = new Storage(filePath);
            tasks = new TaskList(Parser.decode(storage.load()));
        } catch (FileNotFoundException e) {
            ui.showError(e.getMessage());
        }
    }

    private Duke(Ui ui, Storage storage, TaskList taskList) {
        this.ui = ui;
        this.storage = storage;
        this.tasks = taskList;
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            // conversation loop
            try {
                String input = ui.readLine();
                ui.printHorizLine();
                Command cmd = Parser.parseCommand(input);
                cmd.execute(tasks, ui, storage);
                isExit = cmd.exeResult();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printHorizLine();
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
