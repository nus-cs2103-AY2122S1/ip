package duke;


import duke.task.TaskList;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Duke program implements a basic task bot application. He serves like an efficient butler.
 *
 * @author Aiken Wong
 */
public class Duke extends Application {

    private TaskList tasks = new TaskList();
    private Storage storage;

    /**
     * Initialises a new Duke object with storage path data/taskList.txt
     */
    public Duke() {
        this.storage = new Storage("data/taskList.txt");
        try {
            this.tasks = storage.loadTasks();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Initialises a new Duke object by taking in a filePath for storage.
     *
     * @param filePath This will be the location where user tasks are in the hard disk.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = storage.loadTasks();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void start(Stage stage) {
        Ui ui = new Ui(stage);
        ui.showGui(this.tasks, this.storage);
    }
}
