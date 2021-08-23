import java.io.FileNotFoundException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public TaskList getTasks() {
        return this.tasks;
    }
    public Storage getStorage() {
        return this.storage;
    }

    public Ui getUi() {
        return this.ui;
    }

    public Duke(String filePath) {
        ui = new Ui(this);
        storage = new Storage(filePath, this);
        tasks = new TaskList(this);
    }

    private void run() {
        ui.showWelcomeMessage();
        try {
            storage.loadFileToList();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
        } finally {
            String command = ui.getUserCommand();
            Parser parser = new Parser(this);
            parser.handleInput(command);
        }
    }

    public static void main(String[] args) {
        new Duke("src/main/java/data/list.txt").run();
    }
}
