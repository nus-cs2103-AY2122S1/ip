package duke;

import java.io.IOException;

public class Duke {
    TaskList taskList;
    UserInterface userInterface;
    Storage storage;
    Parser parser;

    /**
     * Creates a Duke object.
     *
     * @param filePath The file path to the text file containing the stored tasks.
     */
    public Duke(String filePath) throws IOException {
        userInterface = new UserInterface();
        storage = new Storage(filePath);
        parser = new Parser();

        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            taskList = new TaskList();
            throw e;
        }
    }
}
