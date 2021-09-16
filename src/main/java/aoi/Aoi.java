package aoi;

import aoi.data.TaskList;
import aoi.exceptions.AoiException;
import aoi.parser.Parser;
import aoi.storage.Storage;
import aoi.ui.Ui;

/**
 * Encapsulates a bot that manages tasks for users.
 *
 * @author Owen Tan
 * @version aoi.Aoi Level-9
 */
public class Aoi {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for aoi.Aoi.
     *
     * @param filepath Path for data to be stored.
     */
    public Aoi(String filepath) {
        tasks = new TaskList();
        ui = new Ui(tasks);
        storage = new Storage(filepath, tasks);
        parser = new Parser(tasks, ui);
        storage.load();
    }

    /**
     * Gets the response of Aoi after parsing and executing user's input.
     *
     * @return A string representation of Aoi's response to user's input.
     */
    public String getResponse(String cmd) {
        String response;
        assert cmd != null : "Input is null";
        if (cmd.equals("bye")) {
            return ui.showFarewellMsg();
        }

        try {
            response = parser.parse(cmd);
            storage.save();
        } catch (AoiException e) {
            response = e.getMessage();
        }
        return response;
    }
}
