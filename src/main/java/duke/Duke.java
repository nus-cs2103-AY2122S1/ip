package duke;
/**
 * Main class for Duke, scans for inputs and responds to user.
 */
public class Duke {

    private static final String CACHE_PATH = "data/cache.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for a Duke program.
     * 
     * @param filePath Relative path of cache as string.
     */
    public Duke(String filePath) {
        try {
            storage = new Storage(filePath);
            tasks = storage.load();
            ui = new Ui(tasks, storage);
        } catch (DukeException e){
            Ui.printMsg(new String[] {e.getMessage()});
        }
    }    

    /**
     * Run a Duke program.
     */
    public void run() {
        ui.run();
    }

    /**
     * Create and run a duke program.
     *
     * @param args Will not be used.
     */
    public static void main(String[] args) {
        new Duke(CACHE_PATH).run();
    }
}
