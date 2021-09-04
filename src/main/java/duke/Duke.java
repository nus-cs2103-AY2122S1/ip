package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Duke class to store a list of tasks, which you can add upon.
 * The 3 different tasks include event, todo, and deadline.
 * Duke supports functions such as done, delete, find, and list too.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private final String filePath = "data/tasks.txt";

    /**
     * Constructs Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke program after reading input by user.
     *
     * @param input User's input.
     * @return String to confirm the user's input.
     */
    public String run(String input) {
        while (!storage.isExit()) {
            try {
                Parser p = new Parser(input, ui, storage, tasks);
                return p.parseCommand();
            } catch (DeleteException | DukeException | IOException | StringIndexOutOfBoundsException | duke.FindException e) {
                return e.getMessage();
            }
        }
        return "You have terminated your program, please exit and re-enter";
    }

    /**
     * Starts the Gui.
     *
     * @param stage The stage to initialise Gui.
     */
    @Override
    public void start(Stage stage) {
        Duke duke = new Duke();
        ui.initialiseGui(stage, duke);
    }
}
