package duke;

/**
 * This is Duke, a program that serves as a ToDo-List.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for Duke.
     * Used to initialise Duke.
     *
     * @param filePath String representation of the path where Duke will be run from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());

            ui.greet2();
            ui.displayListUi(taskList);
            ui.showLine();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Runs the program.
     * Will keep running until an exit command is given.
     */
    public void run() {
        ui.greet1();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("src/data/Duke.txt").run();
    }
}
