package bob;

import bob.exception.*;

import java.io.File;

/**
 * Represents a chatbot that stores, displays and alters the user's task list based on the user input.
 */
public class Bob {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for creating a new Bob instance.
     *
     * @param filePath Pathname to bob.txt file that will be used to store the list of tasks.
     */
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

    /**
     * Runs the Bob instance to begin the chat.
     */
    public void run() {
        ui.showGreeting();
        Parser parser = new Parser();
        parser.run(ui, tasks, storage);
        ui.showGoodbye();
    }

    /**
     * Main method that finds the pathname to the storage bob.txt file and creates and runs a new Bob instance.
     *
     * @param args String array that acts as the argument to the main method.
     */
    public static void main(String[] args) {
        String currDirectory = new File("").getAbsolutePath();
        String dataDirectory = currDirectory + "/data";
        new Bob(dataDirectory).run();
    }
}
