package duke;

public class Duke {
    private Parser parser;
    private Storage storage;
    private DukeUi ui;

    public Duke() {
        ui = new DukeUi();
        storage = new Storage();
        storage.autoLoad();
        parser = new Parser(new TaskList(storage));
    }

    public void run() {
        ui.welcome();
        ui.readUserInput(parser);
        ui.exit();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}