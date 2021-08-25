package duke;

/**
 * Duke(Hiko created by me) is a personal assistant.
 *
 * @author Chen Yanyu
 */
public class Duke {
    private static final String PATH = "data/duke.txt";

    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    public Duke(String path) {
        this.storage = new Storage(path);
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(storage.load());
            Ui.showMessage("Saved tasks loaded successfully!");
        } catch (DukeException e) {
            Ui.showError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke(PATH).run();
    }

    private void run() {
        Ui.showGreeting();
        boolean isExit = false;

        while (!isExit) try {
            Ui.showCaret();
            String input = ui.readInput();
            Command command = Parser.parse(input);
            command.execute(tasks, ui, storage);
            isExit = command.isExit();
        } catch (DukeException e) {
            Ui.showError(e.getMessage());
        }
    }
}
