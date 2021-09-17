package duke;

import duke.command.Command;
import duke.util.DataManager;
import duke.util.Parser;
import duke.util.TaskList;

/**
 * This class encapsulates a CLI bot named Duke.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class Duke {
    private final Parser parser;

    /**
     * Constructs a Duke instance that use the specified filepath for persistent storage.
     *
     * @param filePath Path of file used for persistent storage.
     */
    public Duke(String filePath) {
        DataManager dataManager = new DataManager(filePath);
        TaskList list = new TaskList(dataManager.readData(), dataManager);
        parser = new Parser(list, dataManager);
    }

    public String getResponse(String input) {
        Command command = parser.parse(input);

        return command.getResponse(input);
    }
}
