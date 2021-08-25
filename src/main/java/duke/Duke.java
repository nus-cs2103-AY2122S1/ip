package duke;

import duke.command.Command;
import duke.exception.DukeException;

import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.FileNotFoundException;

/**
 * The main class of CS2103 iP.
 * It is the main body of this task tracking software
 * that drives the main logic.
 */
public class Duke {
    private static final String DEFAULT_PATH = "./data/duke.txt";
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public static void main(String[] args) {
        Duke duke = new Duke(DEFAULT_PATH);
        duke.run();
    }

    /**
     * Main logic of Duke,
     * it starts the conversation loop and continuously reads input from
     * user and execute corresponding functions until exit instructions are given.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            // conversation loop
            try {
                String input = ui.readLine();
                ui.printHorizLine();
                Command cmd = Parser.parseCommand(input);
                cmd.execute(tasks, ui, storage);
                isExit = cmd.exeResult();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printHorizLine();
            }
        }
    }

    /**
     * Constructor of Duke.
     * It instantiates an empty which save and load its contents
     * to the default file (./data/duke.txt).
     */
    public Duke() {
        this(new Ui(), new Storage(DEFAULT_PATH), new TaskList());
    }

    /**
     * Constructor of Duke.
     * It instantiates a Duke with initial contents from
     * the file given by the filePath,
     * and associates the Duke's save and load function to that file.
     * @param filePath Path of the file containing initial content of Duke.
     */
    public Duke(String filePath) {
        try {
            ui = new Ui();
            storage = new Storage(filePath);
            tasks = new TaskList();
            tasks.loadFromFile(storage);
        } catch (FileNotFoundException e) {
            ui.showError(e.getMessage());
        }
    }

    private Duke(Ui ui, Storage storage, TaskList taskList) {
        this.ui = ui;
        this.storage = storage;
        this.tasks = taskList;
    }
}
