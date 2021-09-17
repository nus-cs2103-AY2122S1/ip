package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * TsunDuke is a chatbot that keeps track of your tasks for you, with a sassy personality for those who enjoy it.
 *
 * Description:
 * On running the program, TsunDuke greets the user and awaits for the user to input commands
 *
 * CS2103T Individual Project AY 21/22 Sem 1
 * @author Benedict Chua
 */
public class Duke {
    private static final String ERROR_PREFIX = "BAKA!";

    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Constructor for Duke.
     * Initialises the classes that are necessary for Duke to run.
     */
    public Duke() {
        storage = new Storage();
        tasks = new TaskList(storage.retrieveData(), storage);
        parser = new Parser(tasks);
    }

    /**
     * Gets the response from Duke upon receiving user's input command.
     *
     * @param input String containing the user's input command.
     * @return String containing Duke's response to the user's input command.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.getCommand(input);
            return command.execute();
        } catch (DukeException e) {
            return e.toString();
        }
    }

    /**
     * Checks if the response from Duke is an error message.
     *
     * @param message String containing Duke's response.
     * @return boolean of whether Duke's response is an error message.
     */
    public boolean checkIfErrorMessage(String message) {
        return message.startsWith(ERROR_PREFIX);
    }
}
