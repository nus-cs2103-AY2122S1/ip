package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Represents a program called Duke. A <code> Duke </code> is a software which
 * acts as a todolist.
 */
public class Duke {
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;
    private final ArchiveList archiveList;

    /**
     * Constructor for Duke. Initialises Ui, Storage and TaskList objects.
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
                Command c = Parser.parse(userInput, ui, taskList);
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
            Command c = Parser.parse(input, ui, taskList);
            return c.getExecutedString(taskList, archiveList, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Main method for Duke program.
     *
     * @param args Arguments for main method.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

}
