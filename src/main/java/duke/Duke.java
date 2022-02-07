package duke;
import duke.command.Command;
import duke.exception.DukeException;

/**
 * Duke is a chatbot that can read and respond to user input.
 */
public class Duke {

    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Empty constructor.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("./data/tasks.txt");
        taskList = new TaskList(storage.load());
    }
    /**
     * Constructor of Duke.
     * @param filePath File that stores all the tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }

    /**
     * Return the output of command.
     *
     * @param input Input from gui label.
     * @return Output response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return Ui.showError(e.getMessage());
        }
    }
}
