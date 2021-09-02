package viper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import viper.commands.Command;
import viper.exceptions.DukeException;
import viper.parser.Parser;
import viper.storage.Storage;
import viper.tasks.TaskList;
import viper.ui.Ui;

/**
 * A chatbot that manages user input tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Duke, initialise Ui, Storage and TaskList.
     *
     * @param filePath File path of storage.
     * @throws IOException If file cannot be loaded.
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
