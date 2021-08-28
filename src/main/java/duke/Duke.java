package duke;

import java.io.FileNotFoundException;

/**
 * Represents a chatbot that can be run with functionality of a to-do list keeper.
 * Each <code>Duke</code> object has a <code>Storage</code> to load and save a .txt list,
 * <code>TaskList</code> for storing the <code>Task</code>s, and a <code>Ui</code> for interacting
 * with user inputs.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Returns a Duke object
     *
     * @param filePath String of the filepath of the list from source folder.
     */
    public Duke(String filePath) {
        ui = new Ui(this);
        storage = new Storage(filePath, this);
        tasks = new TaskList(this);
    }

    public TaskList getTasks() {
        return this.tasks;
    }
    public Storage getStorage() {
        return this.storage;
    }
    public Ui getUi() {
        return this.ui;
    }

    /**
     * Runs the duke chatbot by loading the TaskList and calling the Ui and Parser
     */
    private void run() {
        ui.showWelcomeMessage();
        try {
            storage.loadFileToList();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
        } finally {
            String command = ui.getUserCommand();
            Parser parser = new Parser(this);
            parser.handleInput(command);
        }
    }

    public static void main(String[] args) {
        new Duke("data/list.txt").run();
    }
}
