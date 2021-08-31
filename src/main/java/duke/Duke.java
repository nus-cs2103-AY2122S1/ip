package duke;

import duke.command.Command;
import duke.command.CommandResult;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Reply;

/**
 * Duke class to encapsulate the high level logic of Duke
 */
public class Duke {
    private final Reply reply;
    private TaskList tasks;
    private final Storage storage;

    /**
     * Constructor of Duke
     *
     * @param filePath path of the save file
     */
    public Duke(String filePath) {
        reply = new Reply();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            reply.showLoadingError();
            tasks = new TaskList();
        }
    }

    public CommandResult getResponse(String input) {
        try {
            String[] parsedInput = Parser.parseInput(input);
            Command c = Parser.parse(parsedInput);
            return c.execute(tasks, reply, storage);
        } catch (DukeException e) {
            return new CommandResult(Reply.showError(e.getMessage()),
                    false, false);
        }
    }
}
