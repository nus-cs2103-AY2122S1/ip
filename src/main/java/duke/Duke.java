package duke;


/**
 * Represents a Duke object. It is the object used to kickstart the Duke application.
 * This application acts as a task tracker to help its users keep track of Deadlines, Events and To-dos.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private Statistics statistics;

    /**
     * Constructor of a Duke object
     *
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        tasks = new TaskList(storage.loadFile());
        statistics = storage.loadStats();
    }

    /**
     * Empty constructor
     */
    public Duke() {

    }

    /**
     * Returns a response based on the given user input.
     */
    String getResponse(String input) throws DukeException {
        String command = input;
        Command c = parser.parse(command);
        return c.execute(tasks, ui, storage, statistics);

    }
    
    /**
     * Returns the greeting message.
     */
    String greetingMessage() {
        return ui.showWelcome();
    }



}
