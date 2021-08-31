package duke;

import java.io.IOException;

import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**<h1>Duke task tracker</h1>
 * The Duke program is an interactive task tracker that helps the user track general tasks, events and deadlines.
 * The program interacts with the user using an {@link duke.utility.Ui} and uses a custom {@link duke.utility.TaskList}
 * class to keep track of tasks. It also makes use of a {@link duke.utility.Storage} class that enables logging of
 * the tasks locally. This saved task list will be read and imported everytime the the program runs, so that the user
 * can pick up where they left off.
 * @author Justin Ngo
 */

public class Duke extends Application {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Initialises a new <code>Duke</code> object with its own {@link duke.utility.Ui} and
     * {@link duke.utility.TaskList}.
     * If possible it will create a {@link duke.utility.Storage} object from a given filepath, which enables loading
     * of previous tasks from that specified file if it exists, or automatically creates such a file for future
     * logging. If error is encountered creating the file, the program will not log tasks for that run of the program.
     * @param logPath File path of previous saved task log (if any).
     */
    public Duke(String logPath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(logPath);
            this.tasks = new TaskList(storage.loadPreviousTasks());
        } catch (IOException ex) {
            System.out.println("Unable to create/open specified file.\nTasks will not be logged.");
            this.storage = null;
            this.tasks = new TaskList();
        }
    }

    public Duke() {
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    private void run() {
        this.ui.startListening(this.tasks, this.storage);
    }

    public static void main(String[] args) {
        new Duke("taskLog.txt").run();
    }
}
