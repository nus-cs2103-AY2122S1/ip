package duke;


import duke.tasks.TaskList;

/**
 * CLI Task manager based on Duke.
 *
 * @author bhcs
 */
public class Duke {

    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    /**
     * Instantiates the Duke object and its required components.
     */
    public Duke() {
        this.storage = new Storage();
        this.tasks = storage.readFromDatabase();
        Parser parser = new Parser(this.tasks);
        ui = new Ui(parser);
        storage.ui = ui;
    }

    protected void run() {
        String logo =
                " ____        _        \n"
                        + "|  _ \\ _   _| | _____ \n"
                        + "| | | | | | | |/ / _ \\\n"
                        + "| |_| | |_| |   <  __/\n"
                        + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + '\n' + "What can I do for you?");
        ui.monitor();
        storage.writeToDatabase(tasks);
    }

    /**
     * Begins main process of Duke.
     *
     * @param args java command line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

}
