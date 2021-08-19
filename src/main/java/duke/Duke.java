package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.exceptions.DukeFileException;

/**
 * This is the duke.Duke class.
 */
public class Duke {

    private Storage store;
    private Ui ui;
    private TaskList taskList;

    /**
     * This is the Constructor of duke.Duke.
     */
    public Duke(String filePath) {
        ui = new Ui();
        this.store = new Storage(filePath);
        try {
            taskList = new TaskList(store.load());
        } catch (DukeFileException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * This is the main point of interaction of user and duke.Duke.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;

        while(!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.decipher(fullCommand);
                c.execute(taskList, store, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }

    }

    public static void main(String[] args) {
        java.nio.file.Path filepath = java.nio.file.Paths.get("src",
                                                "main", "java","data","StoredData.txt");
        new Duke(filepath.toString()).run();
    }

}
