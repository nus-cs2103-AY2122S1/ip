package duke;

import duke.task.DukeList;

/**
 * Encapsulation of Duke's main class.
 */
public class Duke {

    /** Duke's data storage. */
    private final Storage STORAGE;

    /** Duke's Ui. */
    private final Ui UI;

    /**
     * Constructs a Duke object.
     *
     * @param path PATH of where save file for saved data is placed.
     */
    public Duke(String path) {
        DukeList LIST = new DukeList();
        this.STORAGE = new Storage(path, LIST);
        this.UI = new Ui(LIST, this.STORAGE);
    }

    /**
     * Runs Duke.
     */
    public void run() {
        this.STORAGE.load();
        this.UI.run();
    }

    /**
     * Runs Duke.
     *
     * @param args User input.
     */
    public static void main(String[] args) {
        String PATH = System.getProperty("user.dir");

        new Duke(PATH).run();
    }
}
