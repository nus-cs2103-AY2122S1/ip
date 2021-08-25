/**
 * Main class for Duke, scans for inputs and responds to user.
 */
public class Duke {

    private static final String CACHE_PATH = "data/cache.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        try {
            storage = new Storage(filePath);
            tasks = storage.load();
            // load should read and initialize cache
            ui = new Ui(tasks, storage);
        } catch (DukeException e){
            Ui.printMsg(new String[] {e.getMessage()});
        }
    }    

    public void run() {
        ui.run();
    }

    /**
     * Main execution when Duke is run.
     *
     * @param args Will not be used
     */
    public static void main(String[] args) {
        new Duke(CACHE_PATH).run();
    }
}
