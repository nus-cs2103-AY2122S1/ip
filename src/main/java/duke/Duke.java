package duke;

import java.io.File;

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
    private TaskList list;
    private final Storage storage;

    /**
     * A constructor method that creates the driver, with the default storage file path used.
     */
    public Duke() {
        setTaskList(new TaskList());
        this.storage = Storage.createStorage();
        assert new File(Storage.DEFAULT_FILE_PATH).exists();
        try {
            loadTaskListFromStorage();
        } catch (DukeException e) {
            printLoadIssueMessage(e);
        }
    }

    /**
     * A constructor method that creates the driver, with the input filepath used.
     * @param filePath the filepath of the file.
     */
    public Duke(String filePath) {
        setTaskList(new TaskList());
        this.storage = Storage.createStorage(filePath);
        assert new File(filePath).exists();
        try {
            loadTaskListFromStorage();
        } catch (DukeException e) {
            printLoadIssueMessage(e);
        }
    }

    /**
     * Gets command from parser and executes the command.
     * @param input passed by user interface
     * @return CommandResult
     * @throws DukeException if parse is unsuccessful.
     */
    @FXML
    public CommandResult getResponse(String input) throws DukeException {
        return new Parser(this.list).parse(input).execute();
    }

    /**
     * Saves the taskList to disk.
     */
    public void save() {
        this.storage.save(this.list);
    }

    private void setTaskList(TaskList taskList) {
        this.list = taskList;
    }

    private void loadTaskListFromStorage() throws DukeException {
        setTaskList(this.storage.load(this.list));
    }

    private void printLoadIssueMessage(DukeException e) {
        System.out.println(Storage.LOAD_ISSUE_MESSAGE + e.getMessage());
    }
}
