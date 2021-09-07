package duke;

/**
 * Represents the duke bot.
 */
public class Duke {

    enum Category {
        TODO,
        EVENT,
        DEADLINE
    }

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructor for the Duke class.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.load());
    }

    /**
     * Sends an appropriate response to the user.
     *
     * @param input input from the user.
     * @return String format response.
     */
    String getResponse(String input) {
        Command command = Parser.parseCommand(input);
        return command.run(tasks, ui, storage);
    }
}
