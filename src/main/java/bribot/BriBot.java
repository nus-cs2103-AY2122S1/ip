package bribot;

import bribot.command.Command;
import bribot.exception.DukeException;
import bribot.parser.Parser;
import bribot.storage.Storage;
import bribot.task.TaskList;
import bribot.ui.Ui;


/**
 * Represents the main class that the program runs from.
 */
public class BriBot {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialises the program
     */
    public BriBot() {
        this.ui = new Ui();
        this.storage = new Storage("src/main/data/tasks.txt");
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }


    public String getResponse(String input) {
        Command c = Parser.parse(input);

        try {
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.printDukeException(e.getMessage());
        }
    }

    public Ui getUi() {
        return this.ui;
    }

}
