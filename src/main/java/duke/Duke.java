package duke;

import java.io.IOException;

/**
 * A Personal Assistant Chat bot that helps to keep track of tasks
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs Duke object
     *
     * @param filepath file location
     */
    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            System.out.println("Past records (if any):");
            taskList = storage.loadData();
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Activates programme
     */
    public void run() {
        ui.welcome();
        Parser parser = new Parser(this.taskList, this.ui, this.storage);
        parser.parse();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
