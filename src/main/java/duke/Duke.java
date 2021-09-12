package duke;

import java.nio.file.Paths;
import java.util.ArrayList;

import duke.command.Command;
import duke.task.Task;

/**
 * A chat bot to keep track of tasks.
 */
public class Duke {
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;

    /**
     * Class constructor for Duke Class specifying the filepath
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(Paths.get(System.getProperty("user.dir"), "data", "duke.txt"));
        try {
            ArrayList<Task> taskList = storage.load();
            if (taskList.isEmpty()) {
                throw new DukeException("task list is empty");
            }
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
