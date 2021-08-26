import java.io.File;

public class Duke {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(new File(filePath));
        tasks = new TaskList(storage.load());
        parser = new Parser();
    }

    public void run() {
        ui.showWelcomeMessage();

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.getUserInput();
                Command c = parser.parseCommand(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e);
            }
        }

        ui.showGoodbyeMessage();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
