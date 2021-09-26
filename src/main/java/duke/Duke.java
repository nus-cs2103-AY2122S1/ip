package duke;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The Duke class encapsulates the resources and interfaces
 * needed for the Duke program to run.
 */
public class Duke {
    /** Storage used by the Duke program. */
    private Storage storage;

    /** The user's task list in Duke. */
    private TaskList tasks;

    /** The user interface of Duke. */
    private Ui ui;

    /**
     * Constructs the Duke program, initializing all
     * the necessary resources for the program to run.
     */
    public Duke() {
        try {
            this.ui = new Ui();
            this.storage = new Storage();
            this.tasks = new TaskList(Parser.parseSaveFile(this.storage.getSavedContents()));
        } catch (DukeException e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Returns a string to be displayed by the GUI based on the input string entered by the user.
     *
     * @param input The input string entered by the user.
     * @return The string to be displayed by Duke's GUI.
     */
    public String getResponse(String input) {
        try {
            return Parser.parseUserInput(input).execute(this.tasks, this.ui, this.storage);
        } catch (DukeException e) {
            return this.ui.formatErrorMessage(e);
        }
    }

    /**
     * Returns the string to be displayed on initializing Duke's GUI.
     *
     * @return The string to be displayed on initializing Duke's GUI.
     */
    public String getStartMessage() {
        return this.ui.getStartMessage();
    }
}
