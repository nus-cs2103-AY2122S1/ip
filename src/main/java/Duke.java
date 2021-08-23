public class Duke {
    private Storage storage;
    private Ui ui;
    private static final String FILE_PATH = System.getProperty("user.dir");

    public Duke(String filepath) {
        this.storage = new Storage(filepath);
        this.ui = new Ui();
    }

    public void run() {
        this.ui.welcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String inputCommand = ui.readCommand();
                Command c = Parser.parseInput(inputCommand);
                c.execute(storage, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }
}