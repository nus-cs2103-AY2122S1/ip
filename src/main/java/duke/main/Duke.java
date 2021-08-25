package duke.main;

import duke.command.Command;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readPastData());
        } catch (FileNotFoundException e) {
            storage.createDataFile();
            tasks = new TaskList(new ArrayList<>());
        }
    }

    public void run() {
        this.ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userCommand = this.ui.readCommand();
                Command command = Parser.parse(userCommand);
                command.execute(this.tasks, this.ui, this.storage);
            } catch (DukeException e) {
                ui.displayError(e.getMessage());
            }
        }

    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }
}
