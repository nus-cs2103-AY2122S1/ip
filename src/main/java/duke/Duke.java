package duke;

import java.io.IOException;

public class Duke {
    TaskList taskList;
    ResponseGenerator responseGenerator;
    Storage storage;
    Parser parser;

    /**
     * Creates a Duke object.
     *
     * @param filePath The file path to the text file containing the stored tasks.
     */
    public Duke(String filePath) throws IOException {
        responseGenerator = new ResponseGenerator();
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
