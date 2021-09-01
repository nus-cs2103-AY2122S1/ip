package duke;

import java.io.IOException;

import duke.command.Command;
import duke.task.TaskList;

/**
 * Duke class which is the main entry point to run the application.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke object.
     *
     * @param filePath File path of .txt file which stores all the tasks.
     * @throws IOException IOException
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public String getResponse(String userInput) {
        Command cmd = Parser.parse(userInput);
        String outputMsg = cmd.execute(tasks, ui, storage);
        return outputMsg;
    }

}
