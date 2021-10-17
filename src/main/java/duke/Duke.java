package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;


public class Duke {

    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor for Duke object.
     *
     * @param filePath the file path of the document which stores the users tasks.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = this.storage.load();
    }

    /**
     * Gets the program output give the user input.
     *
     * @param input User input
     * @return Duke's response
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(this.taskList, input, this.storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
