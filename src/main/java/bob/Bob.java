package bob;

import bob.exception.DirectoryNotFoundException;
import bob.exception.FileNotFoundException;

import java.io.File;

public class Bob {
    /** Storage object that deals with loading tasks from the file and saving tasks in the file */
    private Storage storage;

    /** List of user tasks */
    private TaskList tasks;

    /** Ui object that deals with interactions with the user */
    private Ui ui;

    public Bob(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        ui.showStart();
        try {
            tasks = new TaskList(storage.load());
        } catch (DirectoryNotFoundException e) {
            ui.showDirectoryLoadingError();
            tasks = new TaskList();
            storage.makeDataDirectory();
            storage.makeBobFile();
        } catch (FileNotFoundException e) {
            ui.showFileLoadingError();
            tasks = new TaskList();
            storage.makeBobFile();
        }
    }

    public void run() {
        ui.showGreeting();
        Parser parser = new Parser();
        parser.run(ui, tasks, storage);
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        String currDirectory = new File("").getAbsolutePath();
        String dataDirectory = currDirectory + "/data";
        new Bob(dataDirectory).run();
    }
}
