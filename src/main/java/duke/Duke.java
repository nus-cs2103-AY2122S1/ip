package duke;

import duke.command.Command;

import java.util.ArrayList;

/**
 * Duke class that starts and runs the Duke bot.
 */
public class Duke {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs the Duke object.
     *
     * @param filePath Path of storage file for Duke.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.getFile());
        } catch (DukeException e) {
            ui.printMessage(e.getMessage());
            taskList = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Runs the Duke bot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }

}
