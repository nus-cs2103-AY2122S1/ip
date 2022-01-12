package duke;

import java.io.IOException;

import duke.util.UI;
import duke.util.Parser;
import duke.util.Storage;
import duke.task.TaskList;
import duke.command.Command;
import duke.exception.DukeException;

/**
 * Duke, a Personal Assistant ChatBot.
 *
 * @author Frederick Pek
 * @version CS2103 AY21/22 Sem 1 iP
 */
public class Duke {
    private final TaskList taskList;
    private final Storage storage;
    private final UI ui;
    private boolean isExit;
    private boolean isExceptionReply;

    /**
     * Constructs an instance of Duke.
     * <p>
     * Duke's data will be stored in a text file denoted by the
     * specified directory and file names in the currect directory.
     *
     * @param dirName the specified directory name to store Duke's data.
     * @param fileName the specified file name to store Duke's data.
     */
    public Duke(String dirName, String fileName) {
        this.storage = new Storage(dirName, fileName);
        this.taskList = new TaskList(storage.loadTasks());
        this.ui = new UI();
        this.isExit = false;
        this.isExceptionReply = false;
    }

    /**
     * Returns the boolean exit status of Duke.
     * 
     * @return Returns the boolean exit status of Duke.
     */
    public boolean getIsExit() {
        return this.isExit;
    }

    /**
     * Returns true if the most recent reply by duke was an error message.
     * 
     * @return Returns true if the most recent reply by duke was an error message.
     */
    public boolean getIsExceptionReply() {
        return this.isExceptionReply;
    }

    /**
     * Returns the UI element of Duke. 
     * 
     * @return Returns the UI element of Duke.
     */
    public UI getUI() {
        return this.ui;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        isExceptionReply = true;
        try {
            Command command = Parser.parse(input);
            command.execute(taskList, ui, storage);
            isExit = command.isExit();
            isExceptionReply = false;
            return command.getOutput();
        } catch (DukeException ex) {
            return ui.getErrorMessage(ex);
        } catch (IOException ex) {
            return ex.getMessage();
        }
    }
}
