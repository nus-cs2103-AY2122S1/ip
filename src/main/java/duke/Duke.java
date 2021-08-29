package duke;

import duke.Ui.Commands;
import duke.command.Command;

/**
 * Handles initialization of storage and tasks and running of Duke chatbot.
 */
public class Duke {
    /** Variables related to initializing Duke */
    private static final String SAVE_FILENAME = "dukeSave.txt";
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Default constructor for Duke.
     * Creates a Duke object with filename dukeSave.txt.
     */
    public Duke() {
        this(SAVE_FILENAME);
    }

    /**
     * Constructor for Duke.
     * Creates a Duke with a Storage and an Ui.
     * Storage will read tasks from specified file to TaskList.
     * If there are errors reading file, TaskList will be empty by default.
     *
     * @param fileName Filename that Storage will save tasks to and read tasks from.
     */
    public Duke(String fileName) {
        this.storage = new Storage(fileName);
        this.ui = new Ui();

        // Read tasks from save file.
        try {
            tasks = this.storage.readTasksFromData();
        } catch (DukeException dukeException) {
            System.out.println(dukeException);

            // If failed to read tasks from save, initialize a new duke.task.Task ArrayList.
            tasks = new TaskList();
        }
    }

    /**
     * Returns Duke's string output from executing input String.
     *
     * @param input User's input String.
     * @return Duke's string output from executing input String.
     */
    public String getResponse(String input) {
        // Check if input is "bye"
        if (!input.equals(Commands.BYE.getCommand())) {
            Command command = Parser.parse(input);
            return command.execute(this.tasks, this.ui, this.storage);
        }

        // If input is "bye" return standard goodbye response.
        return this.ui.getGoodbye();
    }

    /**
     * Returns Ui object specific to this Duke.
     *
     * @return Ui object specific to this Duke.
     */
    public Ui getUi() {
        return ui;
    }

}
