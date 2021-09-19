package duke;

/**
 * Represents the main driver of <code>Duke</code>
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs an instance of <code>Duke</code>
     */
    public Duke() {
        this.storage = new Storage("data/duke.txt");
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(this.storage.loadSave());
        } catch (Exception e) {
            this.ui.showLoadingError();
        }
    }

    /**
     * Returns appropriate response based on input
     * @param input input of user
     * @return <code>String</code> response message
     */
    String getResponse(String input) {
        Command command;
        String message;
        try {
            command = Parser.parse(input);
            assert this.tasks != null;
            assert this.ui != null;
            assert this.storage != null;
            message = command.execute(this.tasks, this.ui, this.storage);
            this.storage.updateFile(this.tasks.getTasks());
        } catch (Exception e) {
            return e.toString();
        }
        return "Duke heard: " + input + "\n" + message;
    }

    /**
     * Returns welcome message
     * @return welcome message
     */
    String getWelcomeMessage() {
        return "Welcome to Duke!\nEnter *help* for commands!";
    }
}
