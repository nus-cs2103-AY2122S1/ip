package Duke;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Recieve recieve;
    private Parser parser;

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
    public static void main(String[] args) throws IOException {

        String dukeFile = "text-ui-test/duke.txt";

        Duke duke = new Duke(dukeFile);
        duke.recieve.run();

        }

    }


