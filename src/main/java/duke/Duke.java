package duke;

public class Duke {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Constructor for a new Duke object.
     *
     * @param filePath File path for the storage file.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        taskList = storage.loadTask();
        ui = new Ui(storage, taskList);
    }

    /**
     * Executes the Duke object.
     */
    public void run() {
        boolean isExit = false;
        ui.greet();
        taskList.printTask();
        while (!isExit) {
            String input = ui.readLine();
            ui.handleInput(input);
            ui.showLine();
            isExit = ui.handleExit();
        }
    }

    /**
     * Creates new Dukc object and runs it.
     * @param args
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}