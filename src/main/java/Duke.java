public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        //try {
            tasks = new TaskList(storage.load());
        /**} catch (DukeException1 e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }*/
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Parser parser = new Parser();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
                ui.showLine();

        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
