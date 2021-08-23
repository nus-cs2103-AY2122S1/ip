package duke;

import duke.task.DukeList;

/**
 * Encapsulation of Duke's main class.
 */
public class Duke {
    /** Duke's list. */
    private DukeList list;

    /** Duke's data storage. */
    private Storage storage;

    /** Duke's Ui. */
    private Ui ui;

    /**
     * Constructs a Duke object.
     *
     * @param path PATH of where save file for saved data is placed.
     */
    public Duke(String path) {
        this.list = new DukeList();
        this.storage = new Storage(path, this.list);
        this.ui = new Ui(this.list, this.storage);
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
        String PATH = System.getProperty("user.dir");

        new Duke(PATH).run();
    }
}
