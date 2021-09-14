package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A Bot that keeps track of the user's task list.
 * Task List can take in three types of tasks:
 * 1. Todo
 * 2. Deadline
 * 3. Event
 * Task List is saved into a File called duke.txt in the computer.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Receive receive;
    private Parser parser;

    /**
     * A public constructor to initialize a session with Duke.
     * @throws IOException Thrown when the file is not found.
     */
    public Duke() throws IOException {
        String dukeFile = "duke.txt";
        storage = new Storage(dukeFile);
        assert storage.load() != null : "Storage file cannot be loaded!";

        try {
            tasks = new TaskList(storage.load());
            parser = new Parser(tasks, storage);
            receive = new Receive(parser);

        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Returns Duke's response after user's input in string format.
     * @param input The user's input.
     * @return Duke's response in string format.
     */
    String getResponse(String input) {
        return receive.run(input);
    }


}


