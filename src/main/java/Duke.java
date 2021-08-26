public class Duke {

    TaskList tasks;
    UI ui;
    Storage storage;
    Parser parser;

    public Duke() {

        storage = new Storage("data/duke.txt");
        tasks = new TaskList(storage.load());
        ui = new UI();
        parser = new Parser();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = parser.parse(fullCommand);
                c.execute(ui, tasks, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        Duke instance = new Duke();
        instance.run();
    }
}