import jared.common.DukeException;
import jared.storage.Storage;
import jared.task.TaskList;
import jared.ui.Ui;

public class Jared {
    private static final String FILE_NAME = "data.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Jared(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData(), storage);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        try {
            storage.loadData();
            ui.runScanner(tasks);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Main function
     */
    public static void main(String[] args) {
        new Jared(FILE_NAME).run();
    }

}

