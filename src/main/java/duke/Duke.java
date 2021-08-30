package duke;

import java.io.FileNotFoundException;

import duke.commands.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Response;


public class Duke {

    private Response response;
    private Storage storage;
    private TaskList tasks;
    private String filePath = "data/duke.txt";

    /**
     * Constructor for the Duke object.
     */
    public Duke() {
        response = new Response();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("Invalid file path.");
            e.printStackTrace();
        }
    }

    public String getResponse(String input) {
        Command c = Parser.parse(input, tasks);
        return c.execute(tasks, response, storage);
    }

}
