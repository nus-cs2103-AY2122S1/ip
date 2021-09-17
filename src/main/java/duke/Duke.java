package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Represents a program called Duke. A <code> Duke </code> is a software which
 * acts as a todolist.
 */
public class Duke {
    /**
     * Represents the Ui of duke.
     */
    private final Ui ui;

    /**
     * Represents the storage of duke.
     */
    private final Storage storage;

    /**
     * Represents the taskList of duke.
     */
    private final TaskList taskList;

    /**
     * Represents the archiveList of duke.
     */
    private final ArchiveList archiveList;

    /**
     * Constructs a Duke object. Initialises Ui, Storage, TaskList and ArchiveList objects.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList(storage.initialise());
        this.archiveList = new ArchiveList(storage.initialiseArchive());
    }

    /**
     * Runs the duke program.
     */
    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readInput();
                Command c = Parser.parse(userInput, ui, taskList, archiveList);
                c.execute(taskList, archiveList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Processes the input string and returns a string to be displayed to
     * the user on the GUI.
     *
     * @param input String input to be processed.
     * @return String to be displayed to the user on the GUI.
     */
    public String processInput(String input) {
        try {
            Command c = Parser.parse(input, ui, taskList, archiveList);
            return c.formatExecutedString(taskList, archiveList, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Executes the main method for Duke program.
     *
     * @param args Arguments for main method.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

}
