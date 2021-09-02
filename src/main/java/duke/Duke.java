package main.java.duke;

import main.java.duke.commands.Command;

import java.io.IOException;

public class Duke {
    protected Storage storage;
    protected TaskList tasks;
    protected MainWindow gui;

    /**
     * Constructs a new Duke object with the given file path.
     *
     * @param filePath file path for creating the text file of interactions
     */
    public Duke(String filePath) {
        gui = new MainWindow();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            gui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the duke programme flow.
     */
    public void run() {
        gui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = gui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, gui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                gui.showError(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
