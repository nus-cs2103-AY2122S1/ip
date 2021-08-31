package duke;

import duke.command.Command;
import duke.command.CommandResult;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Reply;
import duke.util.Storage;
import duke.util.TaskList;


/**
 * Duke class to encapsulate the high level logic of Duke
 */
public class Duke {
    private TaskList tasks;
    private final Storage storage;

    /**
     * Constructor of Duke
     *
     * @param filePath path of the save file
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Parses the input, handles the command and returns a CommandResult
     *
     * @param input Input string from the user
     * @return CommandResult after completing the logic of the command.
     */
    public CommandResult getResponse(String input) {
        try {
            String[] parsedInput = Parser.parseInput(input);
            Command c = Parser.parse(parsedInput);
            return c.execute(tasks, storage);
        } catch (DukeException e) {
            return new CommandResult(Reply.showError(e.getMessage()),
                    false, false);
        }
    }
}
