package aisu;

import java.util.List;

import aisu.command.Command;
import aisu.exception.AisuException;
import aisu.parser.Parser;
import aisu.storage.Storage;
import aisu.task.Task;
import aisu.tasklist.TaskList;
import aisu.ui.Ui;

/**
 * A tasklist chatbot, named Aisu.
 *
 * @author Liaw Xin Yan
 */
public class Aisu {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasklist;

    /**
     * Constructor to initialise Aisu.
     * @param dirPath Directory pathname for where the text file should be saved at.
     * @param fileName The name of the text file to store the data.
     */
    public Aisu(String dirPath, String fileName) {
        assert (dirPath.length() >= 1 && fileName.length() >= 1) : "The directory and or filename cannot be empty.";

        this.ui = new Ui();
        this.storage = new Storage(dirPath, fileName);

        try {
            List<Task> cachedData = this.storage.load();
            this.tasklist = new TaskList(cachedData);
        } catch (AisuException e) {
            this.tasklist = new TaskList();
        }
    }

    /**
     * Gets a response from the user.
     * @param input Input from the user.
     * @return Response from Aisu.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            command.execute(this.tasklist, this.storage, this.ui);
            return command.showUiText();
        } catch (AisuException e) {
            return e.getMessage();
        }
    }
}
