package duke;


import duke.command.Command;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Duke program implements a basic task bot application. He serves like an efficient butler.
 *
 * @author Aiken Wong
 */
public class Duke extends Application {

    private boolean isExit = false;
    private TaskList tasks = new TaskList();
    private Ui ui;
    private Storage storage;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initialises a new Duke object with storage path data/taskList.txt
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data/taskList.txt");
        try {
            this.tasks = storage.load();
        } catch (DukeException e) {
            ui.showException(e);
        }
    }

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

    @Override
    public void start(Stage stage) {

        Duke duke = new Duke();
        GraphicalUserInterface gui = new GraphicalUserInterface(stage, duke.ui, duke.tasks, duke.storage);
    }
}
