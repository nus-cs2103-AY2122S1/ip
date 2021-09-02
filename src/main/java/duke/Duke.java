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
    private String exitCmd = "bye";
    private LogMessage logMessage = new LogMessage();

    /**
     * Constructs Duke Object that stores information in filepath
     * @param filepath Location of stored data
     */
    public Duke(String filepath) {
        this.storage = new Storage(filepath);
        this.tasklist = storage.load(logMessage);
    }

    /**
     * Overloaded constructor of Duke Object with default address
     */
    public Duke() {
        this.storage = new Storage(DEFAULT_ADDRESS);
        this.tasklist = storage.load(logMessage);
    }





    /**
     * Run main program
     */
    public void run() {
        Ui.start();
        boolean isExit = false;
        Ui user = new Ui(storage, tasklist);
        while (!isExit) {
            if (user.readCommand().equals(exitCmd)) {
                break;
            }
        }
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

