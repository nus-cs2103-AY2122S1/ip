public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadFile());
    }

    public void run() {
        ui.greetUser();
        while (true) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                if (c instanceof ExitCommand) {
                    ui.farewellUser();
                    break;
                }
                ui.printLine();
                c.execute(tasks, ui, storage);
                ui.printLine();
            } catch (DukeException e) {
                ui.printLine();
                ui.printsMessage(e.getMessage());
                ui.printLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}

