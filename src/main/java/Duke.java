import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructor for Duke
     * @param filePath the path for the file where data is stored
     */
    public Duke(Path filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.loadData());
        this.ui = new Ui();
    }

    /**
     * Runs Duke
     */
    public void run() {
        this.ui.greet();
        boolean isExit = false;
        
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e);
            }
        }
    }

    public static void main(String[] args) {
        String projectDir = System.getProperty("user.dir");
        Path path = Paths.get(projectDir, "data", "duke.txt");
        new Duke(path).run();
    }
}
