package duke;

import duke.commandresult.CommandResult;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import javafx.fxml.FXML;
/**
 * The driver class of Duke.
 */
public class Duke {
    TaskList list;
    Storage storage;

    /**
     * A constructor method that creates the driver, with the default storage file path used.
     */
    public Duke() {
//        this.userInterface = new UserInterface();
        this.list = new TaskList();
        this.storage = Storage.createStorage();
        try {
            this.list = this.storage.load(this.list);
        } catch (DukeException e) {
            System.out.println("DB file is corrupted.\n" + e.getMessage());
        }    }

    /**
     * A constructor method that creates the driver, with the input filepath used.
     * @param filePath the filepath of the file.
     */
    public Duke(String filePath) {
        this.list = new TaskList();
        this.storage = Storage.createStorage(filePath);
        try {
            this.list = this.storage.load(this.list);
        } catch (DukeException e) {
            System.out.println("DB file is corrupted.\n" + e.getMessage());
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    @FXML
    public CommandResult getResponse(String input) throws DukeException {
        return new Parser(this.list).parse(input);
    }

    public void save() throws DukeException {
        this.storage.save(this.list);
    }
}
