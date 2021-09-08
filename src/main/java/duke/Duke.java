package duke;

/**
 * Represents a Personal Assistant Chatbot that helps a person keeps track of various things.
 *
 * @author Sherman Ng Wei Sheng
 */
public class Duke {
    private final Storage storage;
    private final Ui ui;
    private TaskList list;

    /**
     * Constructor specifying the file path of the database to be stored and retrieved from for this bot.
     *
     * @param filePath File path to store and retrieve the duke bot information.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.list = new TaskList(storage.loadDukeData());
        } catch (DukeException e) {
            this.ui.printAndReturnMessage(e.getMessage());
            this.list = new TaskList();
        }
    }
    /**
     * Starts the Chatbot, listens for user input and executes the command accordingly.
     */
    public void start() {
        ui.printAndReturnGreetingsString();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.getInput();
                Command c = Parser.parse(input);
                c.execute(list, ui, storage);
                isExit = c.isAExitCommand();
            } catch (DukeException e) {
                ui.printAndReturnMessage(e.getMessage());
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(list, ui, storage);
        } catch (DukeException e) {
            ui.printAndReturnMessage(e.getMessage());
            return e.getMessage();
        }
    }
    /**
     * Initializes a Duke object and runs the program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.start();
    }
}

