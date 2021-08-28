package duke;

import duke.command.Command;
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
            this.ui.printErrorMessage(e);
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        boolean isExit = false;
        this.ui.printStartMessage();
        while (!isExit) {
            try {
                String userInput = this.ui.readUserInput();
                Command command = Parser.parseUserInput(userInput);
                command.execute(this.tasks, this.ui, this.storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                this.ui.printErrorMessage(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
