import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError(new DukeException(e.getMessage()));
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();

        Parser.parse(this.tasks);

        try {
            storage.save(this.tasks);
        } catch (IOException e) {
            System.out.println("Error encountered when saving data: " + e.getMessage());
        }

        ui.exit();
    }

    public static void main(String[] args) {
        new Duke("data/mango.txt").run();
    }
}
