package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
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

    /**
     * Constructs Duke.
     *
     * @param filePath Filepath to store.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            ui.showLoadingErrorMessage();
            tasks = new TaskList();
        }
    }

    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            ui.showLoadingErrorMessage();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/tasks.txt");
        duke.run();
    }

    /**
     * Runs the Duke program such that a user is able to
     * input events.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.showIntroMessage();
        while (!storage.isExit() && sc.hasNextLine()) {
            try {
                Parser p = new Parser(sc.nextLine(), ui, storage, tasks);
                p.parseCommand();
            } catch (DeleteException | DukeException | IOException | StringIndexOutOfBoundsException | duke.FindException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Starts the Gui.
     * @param stage The stage to initialise Gui.
     */
    @Override
    public void start(Stage stage) {
        ui.initialiseGui(stage);
    }
}
