package duke;

import task.*;
import ui.*;

/**
 * Allows for the main initialization of the Duke Program
 *
 * @author: Wei Yangken
 */
public class Duke {

    private Storage storage;
    private Tasklist tasklist;
    private String exitCmd = "bye";
    private String defaultAddress = "src/main/java/data/tasklist.txt";

    /**
     * Constructs Duke Object that stores information in filepath
     * @param filepath Location of stored data
     */
    public Duke(String filepath) {
        this.storage = new Storage(filepath);
        this.tasklist = storage.load();
    }

    /**
     * Overloaded constructor of Duke Object with default address
     */
    public Duke() {
        this.storage = new Storage(defaultAddress);
        this.tasklist = storage.load();
    }

    /**
     * Run main program
     */
    public void run() {
        Ui.start();
        boolean isExit = false;
        Ui user = new Ui(storage, tasklist);
        while (!isExit) {
            if(user.readCommand().equals(exitCmd)) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        String filepath = "src/main/java/data/tasklist.txt";
        new Duke(filepath).run();
    }
}

