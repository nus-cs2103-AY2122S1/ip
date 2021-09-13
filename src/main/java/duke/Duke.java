package duke;

/**
 * Represents a chat bot assistant to keep track of tasks.
 * @author Nikki
 * @version 0.1
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * Creates Duke Chat Bot instance.
     */
    public Duke() {
        storage = new Storage();
        ui = new Ui();
        taskList = new TaskList(storage.fileReader(), ui);
        parser = new Parser(ui, storage);
    }

    /**
     * Generates duke response for any user input.
     *
     * @param input User input.
     * @return
     */
    public String getResponse(String input) throws InputError {
        String caseType = parser.caseChecker(input);
        return parser.caseHandler(caseType, input, taskList);
    }
}
