import duke.Parser;
import duke.Storage;
import duke.Ui;
import duke.command.Command;
import duke.task.TaskList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * This class represents Duke who runs the main program. This program acts as a task list manager for
 * users. Users can add to-dos, events and deadlines. They can list out the current tasks, mark tasks
 * as done and delete tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Default constructor for duke, which sets the default filepath to data/duke.txt
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data/duke.txt");
        this.tasks = this.storage.load();
    }

    /**
     * Generates a response based off a user input.
     *
     * @return response string
     */
    String getResponse(String input) {
        Command c = Parser.parse(input);
        return c.execute(tasks, storage, ui);
    }
}
