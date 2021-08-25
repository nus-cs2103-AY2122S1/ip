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

    public Duke(String filepath) {
        storage = new Storage(filepath);
        tasklist = storage.load();
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
        String filepath = "src\\main\\java\\data\\tasklist.txt";
        new Duke(filepath).run();
    }
}

