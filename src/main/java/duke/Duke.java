package duke;

import command.Command;
import duke.exception.DukeException;

public class Duke {
    /** Duke's Storage. Deals with loading tasks from the file and saving tasks in the file. **/
    private final Storage storage;

    /** Duke's TaskList. Containing the data structure for storing tasks. **/
    private TaskList taskList;

    /** Duke's user interface. Deals with interactions with the user. **/
    private final Ui ui;

    /**
     * A public constructor to initialized Duke.
     *
     * @param filePath The given filePath to load and save the tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTaskList());
        } catch (DukeException e) {
            ui.showDukeException(e);
            taskList = new TaskList();
        }
    }

    /**
     * The method to execute Duke.
     */
    public void run() {
        ui.printLogo();
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showDukeException(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/taskList.txt").run();
    }
}
