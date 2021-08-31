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
     * @throws IOException Thrown when the file is not found.
     */
    public Duke() throws IOException {
        String dukeFile = "duke.txt";
        storage = new Storage(dukeFile);
        try {
            tasks = new TaskList(storage.load());
            parser = new Parser(tasks, storage);
            recieve = new Recieve(parser);
//            recieve.run();
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
            System.out.println("Creating new file... Please try again!");
        }
    }

    String welcomeMessage() {
        return "Hello! I'm Duke\n" +
                "To add a Todo, type -> todo <Description> \n" +
                "To add a Deadline, type -> deadline <Description> /by <deadline>\n" +
                "To add an Event, type -> event <Description> /at <details>\n" +
                "To mark as done, type -> done <task list index>\n" +
                "To see all of your tasks, type -> list\n" +
                "To end session, type -> bye\n" +
                "What can I do for you today?";
    }

    /**
     *
     */
    String getResponse(String input) {
        return recieve.run(input);
    }


}


