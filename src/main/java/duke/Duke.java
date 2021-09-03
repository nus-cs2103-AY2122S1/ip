package duke;

import java.util.Scanner;

import duke.command.ExitCommand;

import duke.command.DukeCommand;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulate a bot which helps user to keep track of a list of task.
 *
 * @author Zhi Bin
 * @version Duke Level 10
 */
public class Duke{
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;
    private final Parser parser;
    private boolean isActive;

    /**
     * Constructor of Duke
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList();
        taskList.loadFromList(storage.load());
        parser = new Parser(taskList, storage, ui);
        isActive = true;
    }

    /**
     * Currently a stub, to be replaced.
     *
     * @param input The user's input
     * @return A string containing the response to be displayed under duke
     */
    public String getResponse(String input) {
        if (isActive) {
            try {
                DukeCommand c = parser.processInput(input);
                if (c instanceof ExitCommand) {
                    isActive = false;
                }
                return c.execute();
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
        return "";
    }
}
