import duke.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The Duke program is an application that can store the Tasks you need to do!
 */
public class Duke extends Application {

    private static final String DEFAULT_FILEPATH = "data/taskList.txt";

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructor for a Duke object with the default filePath
     */
    public Duke() {
        this.storage = new Storage(DEFAULT_FILEPATH);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            this.tasks = new TaskList();
            try {
                storage.save(tasks);
            } catch (DukeException ex) {
                // do nothing
            }
        }
        this.ui = new Ui(storage, tasks);
    }

    /**
     * GUI for Duke, using JavaFX
     *
     * @param stage the Stage to show Scenes on
     */
    @Override
    public void start(Stage stage) {
        ui.launchGui(stage);
    }

    /**
     * The main method that makes use of the run method.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Duke();
    }
}
