package Duke;

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
    private Recieve recieve;
    private Parser parser;

    /**
     * A public constructor to initialize a session with Duke.
     *
     * @param filePath The given string for the path of the file where the
     *                 task list of the user is saved into.
     * @throws IOException Thrown when the file is not found.
     */
    public Duke(String filePath) throws IOException {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            parser = new Parser(tasks, storage);
            recieve = new Recieve(parser);
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
            System.out.println("Creating new file... Please try again!");
        }
    }

    /**
     * Main method for Duke.
     *
     * @param args Ignored and unused command line arguments.
     * @throws IOException Thrown when the file is not found.
     */
    public static void main(String[] args) throws IOException {

        String dukeFile = "duke.txt";

        Duke duke = new Duke(dukeFile);
        duke.recieve.run();

        }

    }


