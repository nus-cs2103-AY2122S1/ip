package duke;


import duke.command.Command;
import duke.task.TaskList;


/**
 * Duke program implements a basic task bot application. He serves like an efficient butler.
 *
 * @author Aiken Wong
 */
public class Duke {

    private boolean isExit = false;
    private TaskList tasks = new TaskList();
    private Ui ui;
    private Storage storage;


    /**
     * Initialises a new Duke object by taking in a filePath for storage.
     *
     * @param filePath This will be the location where user tasks are in the hard disk.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = storage.load();
        } catch (DukeException e) {
            ui.showException(e);
        }
    }

    /**
     * This method runs the app.
     */
    private void run() {
        ui.greet();
        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parse(input, ui, tasks, storage);
                command.execute();
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showException(e);
            }
        }

    }

    /**
     * This is the main method which makes use of the run method.
     *
     * @param args
     */
    public static void main(String[] args) {
        Duke duke = new Duke("data/taskList.txt");
        duke.run();
    }

}
