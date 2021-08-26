package duke;

import duke.command.Command;
import duke.task.*;

import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.greet();

       boolean isExit = false;

       while (!isExit) {
            String userInput = ui.readCommand();
            Command cmd = Parser.parse(userInput);
            cmd.execute(tasks, ui, storage);
            isExit = cmd.isExit();
        }

        ui.exit();
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt").run();
    }

}
