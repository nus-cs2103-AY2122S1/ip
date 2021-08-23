package duke;

import java.io.IOException;

public class Duke {
    private static String USER_DIR = System.getProperty("user.dir");
    private DukeTaskList dukeTaskList;
    private Storage storage;
    private boolean isRunning;

    public Duke() {
        dukeTaskList = new DukeTaskList();
        storage = new Storage(USER_DIR, dukeTaskList);
        isRunning = true;
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

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



