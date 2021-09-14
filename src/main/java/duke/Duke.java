package duke;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/** This is the main class for the program. */
public class Duke {
    private static final String FILE_PATH = "data.txt";
    private TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructs an instance of Duke program.
     *
     * @param filePath The path of the file to load saved data from.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (DukeException e) {
            System.out.println(Ui.showLoadingError());
            this.tasks = new TaskList();
        }
    }

    /** Constructs an instance of Duke program with default file path to store/load data from (data.txt). */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data.txt");
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (DukeException e) {
            System.out.println(Ui.showLoadingError());
            this.tasks = new TaskList();
        }
    }

    /** Runs the program. */
    public void run() {
        ui.welcomeMessage();
        boolean isExit = false;

        // Main loop of program
        while (!isExit) {
            try {
                // Deal with user's input
                ui.enterCommand();
                String fullCommand = ui.readInput();
                assert fullCommand != null : "String to be printed out by execute() should not be null.";
                assert !fullCommand.isEmpty() : "Command entered by user should not be empty.";
                ui.lineGenerator();

                // Execute command based on user's input
                duke.parser.Parser parser = new duke.parser.Parser(fullCommand);
                System.out.println(parser.execute(tasks, ui, storage));
                isExit = parser.isByeCommand();
            } catch (DukeException e) {
                System.out.println(ui.printError(e));
            } finally {
                ui.lineGenerator();
            }
        }
    }

    public String getResponse(String input) {
        try {
            duke.parser.Parser parser = new duke.parser.Parser(input);
            return parser.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.printError(e);
        }
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }
}
