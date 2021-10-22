package duke;

/**
 * Duke is a task manager bot.
 */
public class Duke {
    private boolean inSession;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Initialises storage, taskList and ui.
     *
     * @param filePath the relative filePath for storage of dataFile.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadData());
        } catch (DukeException e) {
            ui.showError(e);
            taskList = new TaskList();
        }
    }

    /**
     * Initialises Duke with empty constructor.
     */
    public Duke() {
        storage = new Storage("./data/duke.txt");
        try {
            taskList = new TaskList(storage.loadData());
        } catch (DukeException e) {
            taskList = new TaskList();
        }
    }

    /**
     * Runs Duke until the user inputs a bye command.
     */
    public void run() {
        inSession = true;
        String response;
        Parser parser = new Parser(taskList, storage);
        ui.greet();
        while (inSession) {
            try {
                response = parser.parseCommand(ui.scan());
                if (response.equals("bye")) {
                    inSession = false;
                }
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
        ui.exit();
    }

    /**
     * Returns response from parser given an input string.
     *
     * @param input the input string by the user.
     * @return a response from parser.
     */
    public String getResponse(String input) {
        Parser parser = new Parser(taskList, storage);
        String response;
        try {
            response = parser.parseCommand(input);
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
