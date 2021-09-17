package duke;


import task.Tasklist;
import ui.LogMessage;
import ui.Ui;

/**
 * Allows for the main initialization of the Duke Program
 *
 * @author: Wei Yangken
 */
public class Duke {

    private static final String DEFAULT_ADDRESS = "src/main/java/data/tasklist.txt";
    private Storage storage;
    private Tasklist tasklist;
    private static final String EXIT_CMD = "bye";
    private LogMessage logMessage = new LogMessage();
    private Ui ui;

    /**
     * Constructs Duke Object that stores information in filepath
     * @param filepath Location of stored data
     */
    public Duke(String filepath) {
        this.storage = new Storage(filepath);
        this.tasklist = storage.load(logMessage);
        this.ui = new Ui(storage, tasklist);
    }

    /**
     * Overloaded constructor of Duke Object with default address
     */
    public Duke() {
        this.storage = new Storage(DEFAULT_ADDRESS);
        this.tasklist = storage.load(logMessage);
        this.ui = new Ui(storage, tasklist);
    }

    /**
     * Run main program
     */
    public void run() {
        Ui.start();
        boolean isExit = false;
        while (!isExit) {
            if (this.ui.readCommand().equals(EXIT_CMD)) {
                break;
            }
        }
    }

    /**
     *
     * @return Ui that was initialized with Duke
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * Main function to run Duke Program
     * @param args
     */
    public static void main(String[] args) {
        String filepath = DEFAULT_ADDRESS;
        new Duke(filepath).run();
    }
}

