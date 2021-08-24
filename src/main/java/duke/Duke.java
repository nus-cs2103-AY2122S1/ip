package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates duke with the file path given.
     * @param filePath The file path where the tasks are stored locally.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadFile());
    }

    /**
     * Starts duke.
     */
    public void run() {
        ui.greetUser();
        while (true) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                if (c instanceof ExitCommand) {
                    ui.farewellUser();
                    break;
                }
                ui.printLine();
                c.execute(tasks, ui, storage);
                ui.printLine();
            } catch (DukeException e) {
                ui.printLine();
                ui.printsMessage(e.getMessage());
                ui.printLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}

