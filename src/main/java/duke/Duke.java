package main.java.duke;

import java.io.IOException;

/**
 * Represents a chat bot Duke that performs according to different
 * user input.
 */
public class Duke {
    protected Storage tasksStorage;
    protected Storage contactsStorage;
    protected TaskList tasks;
    protected TaskList contacts;
    protected MainWindow gui;

    /**
     * Constructs a new Duke object with the given file path.
     *
     * @param tasksPath file path for creating the text file for tasks list
     * @param contactsPath file path for creating the text file for contacts list
     */
    public Duke(String tasksPath, String contactsPath) {
        gui = new MainWindow();
        tasksStorage = new Storage(tasksPath);
        contactsStorage = new Storage(contactsPath);
        try {
            contacts = new TaskList(contactsStorage.load());
            tasks = new TaskList(tasksStorage.load());
        } catch (DukeException e) {
            gui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
