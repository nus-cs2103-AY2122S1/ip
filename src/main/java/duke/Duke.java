package duke;
/**
 * Main class. Contains the duke program and its data.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Initialize a Duke instance with the file path for the task list.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    private void run() {
        boolean run = true;
        ui.addTaskList(tasks);
        ui.addStorage(storage);
        while (run) {
            run = ui.nextLine();
        }
    }

}
