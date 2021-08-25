import java.io.IOException;

public class Duke {

    private final Ui ui;
    private TaskList tasks;
    private Storage storage;
    
    public Duke(String logPath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(logPath);
            this.tasks = new TaskList(storage.loadPreviousTasks());
        } catch (IOException ex) {
            System.out.println("Unable to create/open specified file.\nTasks will not be logged.");
            this.storage = null;
            this.tasks = new TaskList();
        }
    }
    
    public void run() {
        this.ui.startListening(this.tasks, this.storage);
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
