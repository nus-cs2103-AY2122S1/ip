import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Represents the Duke program. Manages tasks based on commands received.
 */
public class Duke {
    /** Storage that can write to or retrieve data from a file on hard disk */
    private Storage storage;
    /** List of tasks added */
    private TaskList taskList;
    /** UI of the program */
    private Ui ui;

    /**
     * Constructor of the class 'Duke'.
     *
     * @param filePath Path of the file to retrieve data.
     */
    public Duke(Path filePath) {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.storage = new Storage(filePath, this.taskList);
    }

    /**
     * Runs the duke program.
     */
    public void run() {
        this.ui.showWelcome();
        boolean isRunning = true;
        while (isRunning) {
            String commandString = this.ui.getCommand();
            Command command;
            try {
                command = Parser.parse(commandString);
                isRunning = command.execute(this.taskList, this.storage);
                this.ui.showCommandOutput(command);
            } catch (DukeException dukeException) {
                this.ui.showError(dukeException);
                continue;
            }
        }
    }

    /**
     * Runs the Duke program, prints out messages based on commands received.
     *
     * @param args The command line parameters.
     */
    public static void main(String[] args) {
        String filePath = System.getProperty("user.dir");
        new Duke(Paths.get(filePath, "data", "duke.txt")).run();
    }
}
