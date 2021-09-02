package duke;

/**
 * The Duke bot.
 *
 * @author Luo Zhijie
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke bot
     *
     * @param filePath Path to duke file.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
    }

    public Duke() {

    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            tasks = storage.convertFileToTaskList();
        } catch (Exception e) {
            return e.getMessage();
        }
        Parser parser = new Parser(" ");
        try {
            Command c = parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }
}
