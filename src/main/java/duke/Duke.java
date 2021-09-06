package main.java.duke;

import java.io.IOException;

/**
 * Represents a chat bot Duke that performs according to different
 * user input.
 */
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
}
