package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Gui;
import duke.task.TaskList;
import duke.exception.DukeException;


/**
 * A chatbot based on Project Duke
 * @author KelvinSoo
 * @version Level-10
 */
public class Duke {

    private final Storage storage;
    private final Gui gui;
    private TaskList taskList;
    private final String FILE_PATH = "./data/duke.txt";

    /**
     * A constructor to initialize a chatbot.
     */
    public Duke() {
        this.gui = new Gui();
        storage = new Storage(FILE_PATH);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            taskList = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(taskList, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public Gui getGui() {
        return gui;
    }

}
