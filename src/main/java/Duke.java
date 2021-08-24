import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Represents the Duke program. Manages tasks based on commands received.
 */
public class Duke {
    /** Storage that can tore to or retrieve data from a file on hard disk */
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
        this.storage = new Storage();
        this.taskList = new TaskList();
        this.ui = new Ui();
    }

    /**
     * Runs the duke program.
     */
    public void run() {
        boolean isRunning = true;
        while (isRunning) {
            this.ui.getCommandOutput();
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
