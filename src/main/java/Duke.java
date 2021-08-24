public class Duke {
    private TaskList tasks;
    private final Storage storage;
    private static final String FILE_PATH = "data.txt";
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }



    /** Start of the program */
    public void run() {
        ui.welcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.enterCommand();
                String fullCommand = ui.readInput();
                ui.lineGenerator();
                Parser parser = new Parser(fullCommand);
                isExit = parser.execute(tasks, ui, storage);
            } catch (DukeException e) {
                ui.printError(e);
            } finally {
                ui.lineGenerator();
            }
        }
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }
}
