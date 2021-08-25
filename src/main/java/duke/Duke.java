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

    public Duke(String filepath) {
        this.storage = new Storage(filepath);
        this.tasklist = storage.load();
    }

    public Duke() {
        this.storage = new Storage(defaultAddress);
        this.tasklist = storage.load();
    }

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

