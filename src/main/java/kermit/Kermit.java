package kermit;

import kermit.command.Command;

/**
 * Driver class for Kermit, a frog that keeps tracks of your tasks.
 */
public class Kermit {
    private final Storage storage;
    private TaskList taskList;
    private final Response response;

    /**
     * Constructor for Kermit.
     *
     * @param filePath File path for where data for task list should be stored.
     */
    public Kermit(String filePath) {
        response = new Response();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (KermitException e) {
            taskList = new TaskList();
        }
    }

    /**
     * Parses user input, processes it and get Kermit's response.
     *
     * @param input user input.
     * @return String of what kermit says.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            assert command != null : "Parser returned a null command";
            return command.execute(taskList, response, storage);
        } catch (KermitException e) {
            return response.getErrorMessage(e.getMessage());
        }
    }
}
