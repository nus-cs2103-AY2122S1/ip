package misaki;

import java.io.IOException;

import misaki.command.CommandExecutor;
import misaki.exceptions.MisakiException;
import misaki.storage.Storage;
import misaki.tasklist.TaskList;
import misaki.ui.Ui;

/**
 * The Misaki program implements a chat bot called Misaki that supports queries
 * such as creating, marking and deleting tasks.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */

public class Misaki {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private CommandExecutor commandExecutor;

    /**
     * A constructor for Misaki.
     *
     * @param filePath Input path for the data file.
     */
    public Misaki(String filePath) {
        try {
            ui = new Ui();
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadTask());
            commandExecutor = new CommandExecutor(tasks, storage, ui);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    /**
     * Returns response from Misaki.
     *
     * @param input User input command.
     * @return String representation of Misaki's response.
     */
    public String getResponse(String input) {
        String response;
        try {
            response = commandExecutor.execute(input);
        } catch (MisakiException e) {
            response = e.getMessage();
        }
        return response;
    }
}
