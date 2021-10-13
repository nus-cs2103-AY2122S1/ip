package aoi;

import aoi.commands.Command;
import aoi.data.TaskList;
import aoi.exceptions.AoiException;
import aoi.parser.Parser;
import aoi.storage.Storage;

/**
 * Encapsulates a bot that manages tasks for users.
 *
 * @author Owen Tan
 * @version aoi.Aoi Level-9
 */
public class Aoi {
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for aoi.Aoi.
     *
     * @param filepath Path for data to be stored.
     */
    public Aoi(String filepath) {
        tasks = new TaskList();
        storage = new Storage(filepath, tasks);
        storage.load();
    }

    /**
     * Gets the response of Aoi after parsing and executing user's input.
     *
     * @return A string representation of Aoi's response to user's input.
     */
    public String getResponse(String input) {
        String response;
        assert input != null : "Input is null";
        try {
            Command command = Parser.parse(input);
            response = command.execute(tasks, storage);
            storage.save();
        } catch (AoiException e) {
            response = e.getMessage();
        }
        return response;
    }
}
