package duke;

import duke.command.Command;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Executes commands from user input for record keeping of tasks.
 * Exits the programme when "bye" is entered.
 */
public class Duke {
    
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    
    public Duke(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }
    
    /**
     * Executes the main programme where Duke is activated to receive user input and perform corresponding operations.
     * Exits when "bye" is entered.
     *
     * @param args A String array from user input
     * @throws DukeException Exception that is specific to Duke where invalid input is detected
     */
    public static void main(String[] args) throws DukeException, IOException {
        String currDir = new File("").getAbsolutePath();
        Path filePath = Paths.get(currDir, "data", "duke.txt");
        new Duke(filePath).run();
    }
}
