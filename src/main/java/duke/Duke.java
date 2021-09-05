package duke;

import duke.command.DukeCommand;
import duke.exception.CorruptedFileException;
import duke.exception.DukeException;

/**
 * Main class of duke package.
 */
public class Duke {

    protected DukeList list;

    /**
     * Public constructor to create a Duke Object.
     */
    public Duke() {
        list = new DukeList();
        try {
            Storage.load(list);
        } catch (CorruptedFileException e) {
            list = new DukeList();
        }
        assert list != null : "List should be created";
    }

    public String getResponse(String input) {
        String response = "";
        DukeCommand command = Parser.parseInput(input);
        try {
            response = command.run(list);
        } catch (DukeException e) {
            response = e.getMessage();
        }
        assert !response.equals("") : "Response should not be empty";
        return response;
    }
}
