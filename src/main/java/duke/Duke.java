package duke;

import java.io.IOException;

public class Duke {
    private static String userDir = System.getProperty("user.dir");
    private DukeTaskList dukeTaskList;
    private Storage storage;
    private boolean isRunning;

    /**
     * Constructor for the Duke class.
     */
    public Duke() {
        dukeTaskList = new DukeTaskList();
        storage = new Storage(userDir, dukeTaskList);
        isRunning = true;
    }

    /**
     * Main method.
     *
     * @param args java main args.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Run Duke: load data from storage file, print welcome message,
     * execute user inputs, and finally store data to file.
     */
    public void run() {
        try {
            storage.loadDataFile();
            Ui.printWelcome();
            while (isRunning) {
                String uerInput = Ui.readInput();
                Parser.parse(uerInput, dukeTaskList);
                isRunning = Parser.isRunning();
            }
            storage.saveToDataFile();
        } catch (IOException e) {
            System.out.println("Data file cannot be created or written to");
        }
    }
}



