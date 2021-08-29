package duke;

import duke.task.DukeList;

/**
 * Encapsulation of Duke's main class.
 */
public class Duke {

    /** Duke's data storage. */
    private final Storage storage;

    /** Duke's Ui. */
    private final Ui ui;

    /**
     * Constructs a Duke object.
     *
     * @param path PATH of where save file for saved data is placed.
     */
    public Duke(String path) {
        DukeList list = new DukeList();
        this.storage = new Storage(path, list);
        this.ui = new Ui(list, this.storage);
    }

    /**
     * Runs Duke.
     */
    public void run() {
        this.storage.load();
        this.ui.run();
    }

    /**
     * Runs Duke.
     *
     * @param args User input.
     */
    public static void main(String[] args) {
        String path = System.getProperty("user.dir");

        new Duke(path).run();
    }
}
