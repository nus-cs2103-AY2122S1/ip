package duke;

/**
 * Represents the main driver of <code>Duke</code>
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs an instance of <code>Duke</code>.
     * @param filePath File path to file storing tasks
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(this.storage.loadSave());
        } catch (Exception e) {
            this.ui.showLoadingError();
        }
    }

    /**
     * Runs <code>Duke</code> program
     */
    public void run() {
        this.ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = this.ui.getNextLine();
                Command command = Parser.parse(fullCommand);
                command.execute(this.tasks, this.ui, this.storage);
                this.storage.updateFile(this.tasks.getTasks());
                isExit = command.isBye();
            } catch (Exception e) {
                ui.showError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
