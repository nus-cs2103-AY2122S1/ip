package duke;

import duke.commands.Command;

/**
 * Class that contains Duke
 *
 */
public class Duke {

    /** List of tasks that is used in Duke */
    private TaskList taskList;

    /** The interactions used by Duke with the user */
    private Ui ui = new Ui();

    /** Storage for Duke to store data */
    private DukeStorage storage;

    /**
     * Constructor that initializes the tasklist, ui and storage of Duke
     *
     * @param path The destination of the stored data
     */
    public Duke(String path) {
        this.storage = new DukeStorage(path);
        try {
            this.taskList = this.storage.readTasks();
        } catch (DukeException e) {
            taskList = new TaskList();
        }
    }

    /**
     * Returns a String to be displayed as duke's response
     *
     * @param input The input string that the user enters
     * @return The response string that duke gives to be displayed
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }

    }
}
