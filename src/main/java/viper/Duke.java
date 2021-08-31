package viper;

import commands.Command;
import exceptions.DukeException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

/**
 * A chatbot that manages user input tasks.
 */
public class Duke {
    
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Duke, initialise Ui, Storage and TaskList
     * @param filePath
     * @throws IOException
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException | DukeException | ParseException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts duke.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.printLine(e.getMessage());
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        Duke duke = new Duke("data/tasks.txt");
        duke.run();
    }

}
