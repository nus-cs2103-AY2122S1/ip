package duke;

import command.Command;
import duke.exception.DukeException;



public class Duke {
    /** Duke's Storage. Deals with loading tasks from the file and saving tasks in the file. **/
    private Storage storage;

    /** Duke's TaskList. Containing the data structure for storing tasks. **/
    private TaskList taskList;

    /** Duke's user interface. Deals with interactions with the user. **/
    private final Ui ui;

    /**
     * A public constructor to initialized Duke.
     */
    public Duke() {
        this.ui = new Ui(this);
        try {
            this.storage = new Storage("data/taskList.txt");
            this.taskList = new TaskList(storage.loadTaskList());
        } catch (DukeException e) {
            this.ui.generateDukeResponse(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    /**
     * A public constructor to initialized Duke.
     *
     * @param filePath The given filePath to load and save the tasks.
     */
    public Duke(String filePath) {
        ui = new Ui(this);
        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.loadTaskList());
        } catch (DukeException e) {
            ui.generateDukeResponse(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * A method to get Duke response.
     *
     * @param input The given user input.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String executionMessage = c.execute(taskList, ui, storage);
            if (c.isExit()) {
                return ui.generateDukeResponse(executionMessage);
            }
            return ui.generateDukeResponse(executionMessage);
        } catch (DukeException e) {
            return ui.generateDukeResponse(e.getMessage());
        }
    }

    public Ui getUi() {
        return this.ui;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }
}
