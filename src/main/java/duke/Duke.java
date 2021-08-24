package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    protected static List<Task> todoList;
    protected Storage storage;

    /**
     * Generate new Duke
     *
     * @param filePath path to store data
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        todoList = new ArrayList<>();
        try {
            storage.createFile();
            storage.download();
        } catch (IOException e) {
            e.printStackTrace();
            todoList = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("/Users/hungkhoaitay/Duke.Duke/data/duke.txt");
        duke.run();
        duke.finish();
    }

    /**
     * Print Welcome message and process user's command
     */
    public void run() {
        Ui.printWelcome();
        Command.process();
    }

    /**
     * Updating user's data into hard-drive
     */
    public void finish() {
        try {
            this.storage.upload();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
