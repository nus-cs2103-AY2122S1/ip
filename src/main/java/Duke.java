public class Duke {
    private Parser parser;
    private Storage storage;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        storage.autoLoad();
        parser = new Parser(new TaskList(storage,ui),ui);
    }

    public void run() {
        ui.welcome();
        parser.parse();
        ui.exit();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}